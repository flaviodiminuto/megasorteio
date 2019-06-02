package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.enumeradores.Campos
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia

class ApostaDao(context: Context)  {
    private  val banco = Banco(context)

    fun salvar(aposta : Aposta): Long{
        var cv = ContentValues()
        when(aposta.idAposta){
            0L -> cv.putNull(Campos.APOSTA_ID.nome)
            else ->cv.put(Campos.APOSTA_ID.nome,aposta.idAposta)
        }
        cv.put(Campos.APOSTA_VALOR.nome, aposta.valor)
        cv.put(Campos.APOSTA_QUANTIDADE_SEQUENCIAS.nome, aposta.quantidadeSequencias)
        return banco.use().insert(Campos.APOSTA_TABLE.nome, null, cv)
    }

    fun listarTodosSemSequencia(): MutableList<Aposta> {
        val sql = "SELECT * FROM ${Campos.APOSTA_TABLE.nome}"
        var apostas = mutableListOf<Aposta>()
        var cursor : Cursor? = null
        try {
            cursor = banco.use().rawQuery(sql, null)
        }catch (e : SQLException){
            return mutableListOf()
        }
        if(cursor!!.isBeforeFirst)
        while (cursor.moveToNext()){
            var aposta = Aposta()
            aposta.idAposta = cursor.getInt(cursor.getColumnIndex(Campos.APOSTA_ID.nome)).toLong()
            aposta.valor = cursor.getDouble(cursor.getColumnIndex(Campos.APOSTA_VALOR.nome))
            aposta.quantidadeSequencias = cursor.getInt(cursor.getColumnIndex(Campos.APOSTA_QUANTIDADE_SEQUENCIAS.nome)).toLong()
            aposta.sequencias = mutableListOf()
            apostas.add(aposta)
        }
        return apostas
    }

}