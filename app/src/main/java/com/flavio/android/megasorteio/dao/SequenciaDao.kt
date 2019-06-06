package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.enumeradores.Campos
import com.flavio.android.megasorteio.model.Sequencia
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

class SequenciaDao(context: Context) {
    private val banco = Banco(context)

    fun salvarSequencia(sequencia: Sequencia):Long{
        val cv = preencheCV(sequencia)
        return banco.use().insert(Campos.SEQUENCIA_TABLE.nome, null, cv)
    }
    fun consultarSequencia(id : Int): Sequencia {
        var sql = "SELECT * FROM ${Campos.SEQUENCIA_TABLE.nome} " +
                "WHERE ${Campos.SEQUENCIA_ID.nome}=$id "
        var cursor : Cursor?
        return try {
            cursor = banco.use().rawQuery(sql, null)
            if(cursor!!.moveToFirst())
                preencheCamposSequencia( cursor)
            else
                Sequencia()
        }catch (e : SQLException){
            Sequencia()
        }
    }
    fun preencheCamposSequencia(cursor: Cursor):Sequencia{
        var sequencia = Sequencia()
        sequencia.idSequencia = cursor.getInt(cursor.getColumnIndex(Campos.SEQUENCIA_ID.nome)).toLong()
        sequencia.valor = cursor.getDouble(cursor.getColumnIndex(Campos.SEQUENCIA_VALOR.nome))
        sequencia.tamanho = cursor.getInt(cursor.getColumnIndex(Campos.SEQUENCIA_TAMANHO.nome))
        sequencia.dataCriacao = stringParaData(cursor.getString(cursor.getColumnIndex(Campos.SEQUENCIA_DATA_CADASTRO.nome)))
        sequencia.dataAtualizacao = stringParaData(cursor.getString(cursor.getColumnIndex(Campos.SEQUENCIA_DATA_ATUALIZACAO.nome)))
        sequencia.numeros = preencheNumeros(cursor, sequencia.tamanho)
        return sequencia
    }
    private fun stringParaData(dataString : String): Date {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        var localDateTime = LocalDateTime.parse(dataString, format)
        var instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        return Date.from(instant)
    }
    private fun preencheNumeros(cursor: Cursor,tamanho : Int): MutableList<Int>{
        var numeros = mutableListOf<Int>()
        for(i in  1 .. tamanho){
            numeros.add(cursor.getInt(cursor.getColumnIndex("n$i")))
        }
        return numeros
    }
    private fun  preencheCV(sequencia : Sequencia): ContentValues{
        var cv = ContentValues()
        if(sequencia.idSequencia<=0)
            cv.putNull(Campos.SEQUENCIA_ID.nome)
        else
            cv.put(Campos.SEQUENCIA_ID.nome, sequencia.idSequencia)

        cv.put(Campos.SEQUENCIA_VALOR.nome, sequencia.valor)
        cv.put(Campos.SEQUENCIA_TAMANHO.nome, sequencia.tamanho)
        //ano,mes, dia
        //hora, minuto, segundos
        cv.put(Campos.SEQUENCIA_DATA_CADASTRO.nome, sequencia.dataCriacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formater))
        cv.put(Campos.SEQUENCIA_DATA_ATUALIZACAO.nome,LocalDateTime.now().format(formater))
        for(i : Int in 0 .. 14){
            if(i<sequencia.tamanho){
                cv.put("n${i+1}",sequencia.numeros[i])
            }else{
                cv.putNull("n${i+1}")
            }
        }
        return cv
    }
    fun atualizarSequencia(sequencia: Sequencia): Long {
        var cv = preencheCV(sequencia)
        return banco.use().update(Campos.SEQUENCIA_TABLE.nome,cv," ${Campos.SEQUENCIA_ID.nome}=${sequencia.idSequencia} ", null).toLong()
    }
    fun deletarSequencia(sequencia : Sequencia): Int {
        return banco.use().delete( Campos.SEQUENCIA_TABLE.nome, " ${Campos.SEQUENCIA_ID.nome}=${sequencia.idSequencia} ",null)
    }
}