package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.enumeradores.Campos
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia

class ApostaSequenciaDao (private val context : Context){
    private val banco = Banco(context)

    fun saveApostaSequencia(aposta : Aposta): Long {
        if(aposta.idAposta==0L)
            aposta.idAposta = ApostaDao(context).salvar(aposta)
        else
            ApostaDao(context).atualizaAposta(aposta)
        var sd = SequenciaDao(context)
        for(sequencia : Sequencia in aposta.sequencias){
            if(sequencia.idSequencia ==0L) {
                sequencia.idSequencia = sd.salvarSequencia(sequencia)
                var cv = ContentValues()
                cv.putNull(Campos.APOSTA_SEQUENCIA_ID.nome)
                cv.put(Campos.APOSTA_SEQUENCIA_SEQUENCIA.nome, sequencia.idSequencia)
                cv.put(Campos.APOSTA_SEQUENCIA_APOSTA.nome, aposta.idAposta)

                banco.use().insert(Campos.APOSTA_SEQUENCIA_TABLE.nome, null, cv)
            }else{
                Controller(context).atualizarSequencia(sequencia)
            }
        }
        return aposta.idAposta
    }

    fun consultarApostaSequencia(id : Long): Aposta {
        var aposta = Aposta()
        var sql = "SELECT a.*, s.* " +
                " FROM ${Campos.APOSTA_SEQUENCIA_TABLE.nome} apsq " +
                " INNER JOIN ${Campos.APOSTA_TABLE.nome} a " +
                " ON a.${Campos.APOSTA_ID.nome}=apsq.${Campos.APOSTA_SEQUENCIA_APOSTA.nome} " +
                " INNER JOIN ${Campos.SEQUENCIA_TABLE.nome} s " +
                " ON s.${Campos.SEQUENCIA_ID.nome}=apsq.${Campos.APOSTA_SEQUENCIA_SEQUENCIA.nome} " +
                " WHERE apsq.${Campos.APOSTA_SEQUENCIA_APOSTA.nome}=$id"
        var cursor : Cursor? = null
        return try {
            cursor = banco.use().rawQuery(sql, null)
            if(cursor!!.moveToFirst()) {
                var ad = ApostaDao(context)  // para reaproveitar o metodo de preenchimento
                var sd = SequenciaDao(context)  // para reaproveitar o metodo de preenchimento
                aposta = ad.preencheCamposAposta(cursor)
                do {
                    aposta.sequencias.add( sd.preencheCamposSequencia(cursor))
                }while (cursor.moveToNext())
                aposta
            }else
                Aposta()
        }catch (e : SQLException){
            Aposta()
        }
    }

    fun deletarApostaSequencia(aposta: Aposta){
        var where = " ${Campos.SEQUENCIA_ID.nome} in (" +
                " SELECT ${Campos.APOSTA_SEQUENCIA_SEQUENCIA.nome} FROM ${Campos.APOSTA_SEQUENCIA_TABLE.nome} " +
                " WHERE ${Campos.APOSTA_SEQUENCIA_APOSTA.nome}=${aposta.idAposta})"
        banco.use().delete(Campos.SEQUENCIA_TABLE.nome,where,null)
        banco.use().delete(Campos.APOSTA_TABLE.nome, " ${Campos.APOSTA_ID.nome}=${aposta.idAposta} ", null)
    }
    fun verificarSorteio(numeros : MutableList<Int>): MutableList<Aposta> {
        var apostas = mutableListOf<Aposta>()
        var where  = getWhereNumeros(numeros)
        var sql = "SELECT a.*, s.* " +
                " FROM ${Campos.APOSTA_SEQUENCIA_TABLE.nome} apsq " +
                " INNER JOIN ${Campos.APOSTA_TABLE.nome} a " +
                " ON a.${Campos.APOSTA_ID.nome}=apsq.${Campos.APOSTA_SEQUENCIA_APOSTA.nome} " +
                " INNER JOIN ${Campos.SEQUENCIA_TABLE.nome} s " +
                " ON s.${Campos.SEQUENCIA_ID.nome}=apsq.${Campos.APOSTA_SEQUENCIA_SEQUENCIA.nome} " +
                " WHERE $where "
        var cursor : Cursor? = null
        return try {
            cursor = banco.use().rawQuery(sql, null)
            if(cursor!!.isBeforeFirst) {
                var ad = ApostaDao(context)  // para reaproveitar o metodo de preenchimento
                var sd = SequenciaDao(context)  // para reaproveitar o metodo de preenchimento
                while(cursor.moveToNext()) {
                    var aposta = ad.preencheCamposAposta(cursor)
                    do {
                        aposta.sequencias.add(sd.preencheCamposSequencia(cursor))
                    } while (cursor.moveToNext())
                    apostas.add(aposta)
                }
                return apostas
            }else
                mutableListOf()
        }catch (e : SQLException){
            mutableListOf()
        }
    }
    fun getWhereNumeros(numeros: MutableList<Int>): String {
        var where = ""
        for(numero in numeros) {
            where += " ${Campos.SEQUENCIA_NUMEROS.nome} LIKE '%|$numero|%' AND"
        }
        return where.substring(0,where.length-4)
    }
}