package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.model.Sequencia
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
private const val table_name = "sequencia"
private const val col_id_sequencia = "id_sequencia"
private const val col_id_aposta = "id_aposta"
private const val col_valor = "valor"
private const val col_data_criacao = "data_cadastro"
private const val col_data_atualizacao = "data_atualizacao"
private const val col_n1 = "n1"
private const val col_n2 = "n2"
private const val col_n3 = "n3"
private const val col_n4 = "n4"
private const val col_n5 = "n5"
private const val col_n6 = "n6"
private const val col_n7 = "n7"
private const val col_n8 = "n8"
private const val col_n9 = "n9"
private const val col_n10 = "n10"
private const val col_n11 = "n11"
private const val col_n12 = "n12"
private const val col_n13 = "n13"
private const val col_n14 = "n14"
private const val col_n15 = "n15"

class SequenciaDao(context: Context) {
    private val banco = Banco(context)

    fun save(sequencia: Sequencia, id_aposta : Int):Long{
        val cv = preencheCV(sequencia, id_aposta)
        return banco.use().insert(table_name, null, cv)
    }
    private fun  preencheCV(sequencia : Sequencia, id_aposta: Int): ContentValues{
        var cv = ContentValues()
        cv.put(col_id_aposta, id_aposta)
        when(sequencia.id_sequencia){
            0 -> cv.putNull(col_id_sequencia)
            else -> cv.put(col_id_sequencia, sequencia.id_sequencia)
        }
        cv.put(col_valor, sequencia.valor)
        //ano,mes, dia
        //hora, minuto, segundos
        cv.put(col_data_criacao, sequencia.dataCriacao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formater))
        cv.put(col_data_atualizacao,LocalDateTime.now().format(formater))
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