package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.enumeradores.Campos
import com.flavio.android.megasorteio.model.Aposta

class ApostaDao(context: Context)  {
    private  val banco = Banco(context)

    fun salvar(aposta : Aposta): Long{
        var cv = preencheCV(aposta)
        return banco.use().insert(Campos.APOSTA_TABLE.nome, null, cv)
    }

    private fun preencheCV(aposta: Aposta): ContentValues {
        var cv = ContentValues()
        if (aposta.idAposta <= 0)
            cv.putNull(Campos.APOSTA_ID.nome)
        else
            cv.put(Campos.APOSTA_ID.nome, aposta.idAposta)

        cv.put(Campos.APOSTA_VALOR.nome, aposta.valor)
        cv.put(Campos.APOSTA_QUANTIDADE_SEQUENCIAS.nome, aposta.quantidadeSequencias)
        return cv
    }

    fun listarTodasApostas(): MutableList<Aposta> {
        val sql = "SELECT * FROM ${Campos.APOSTA_TABLE.nome}"
        var apostas = mutableListOf<Aposta>()
        var cursor : Cursor? = null
        try {
            cursor = banco.use().rawQuery(sql, null)
            if(cursor!!.isBeforeFirst)
                while (cursor.moveToNext()){
                    var aposta = preencheCamposAposta(cursor)
                    apostas.add(aposta)
                }
            return apostas
        }catch (e : SQLException){
            return mutableListOf()
        }
    }

    fun consultarAposta(id : Long): Aposta {
        var sql = "SELECT * FROM ${Campos.APOSTA_TABLE} WHERE ${Campos.APOSTA_ID} = $id"
        var cursor : Cursor? = null
        try {
            cursor = banco.use().rawQuery(sql, null)
            if(cursor!=null && cursor!!.moveToFirst())
                return preencheCamposAposta( cursor)
            else
                return Aposta()
        }catch (e : SQLException){
            return Aposta()
        }
    }

    fun preencheCamposAposta(cursor: Cursor): Aposta {
        var aposta = Aposta()
        aposta.idAposta = cursor.getInt(cursor.getColumnIndex(Campos.APOSTA_ID.nome)).toLong()
        aposta.valor = cursor.getDouble(cursor.getColumnIndex(Campos.APOSTA_VALOR.nome))
        aposta.quantidadeSequencias = cursor.getInt(cursor.getColumnIndex(Campos.APOSTA_QUANTIDADE_SEQUENCIAS.nome)).toLong()
        aposta.sequencias = mutableListOf()
        return aposta
    }
    fun atualizaAposta(aposta:Aposta): Long {
        var cv = preencheCV(aposta)
        var retorno =  banco.use().update(Campos.APOSTA_TABLE.nome, cv, " ${Campos.APOSTA_ID.nome}=${aposta.idAposta}",null).toLong()
        return retorno
    }

    fun deletarAposta(aposta: Aposta): Int {
        return banco.use().delete(Campos.APOSTA_TABLE.nome," ${Campos.APOSTA_ID.nome}=${aposta.idAposta} ",null )
    }
}