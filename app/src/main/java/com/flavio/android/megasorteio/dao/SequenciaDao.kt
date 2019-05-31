package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.enumeradores.Campos
import com.flavio.android.megasorteio.model.Sequencia
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

class SequenciaDao(context: Context) {
    private val banco = Banco(context)

    fun save(sequencia: Sequencia):Long{
        val cv = preencheCV(sequencia)
        return banco.use().insert(Campos.SEQUENCIA_TABLE.nome, null, cv)
    }
    private fun  preencheCV(sequencia : Sequencia): ContentValues{
        var cv = ContentValues()
        when(sequencia.id_sequencia){
            0L -> cv.putNull(Campos.SEQUENCIA_ID.nome)
            else -> cv.put(Campos.SEQUENCIA_ID.nome, sequencia.id_sequencia)
        }
        cv.put(Campos.SEQUENCIA_VALOR.nome, sequencia.valor)
        //ano,mes, dia
        //hora, minuto, segundos
        cv.put(Campos.SEQUENCIA_DATA_CADASTRO.nome, sequencia.dataCriacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formater))
        cv.put(Campos.SEQUENCIA_DATA_ATUALIZACAO.nome,LocalDateTime.now().format(formater))
        for(i : Int in 1 .. 15){
            if(i<=sequencia.tamanho){
                cv.put("n$i",sequencia.numeros[i-1])
            }else{
                cv.putNull("n$i")
            }
        }
        return cv;
    }
}