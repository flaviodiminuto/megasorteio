package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.model.Aposta

private const val table_name = "aposta"
private const val col_id = "id_aposta"
private const val col_valor = "valor"
class ApostaDao(context: Context)  {
    private  val banco = Banco(context)

    fun save(aposta : Aposta): Long{
        var cv = ContentValues()
        when(aposta.idAposta){
            0L -> cv.putNull(col_id)
            else ->cv.put(col_id,aposta.idAposta)
        }
        cv.put(col_valor, aposta.valor)
        return banco.use().insert(table_name, null, cv)
    }
}