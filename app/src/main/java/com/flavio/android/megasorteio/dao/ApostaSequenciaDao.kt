package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.enumeradores.Campos
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia

class ApostaSequenciaDao (context : Context){
    private val banco = Banco(context)

    fun save(aposta : Long, sequencia : Long): Long{
        var cv = ContentValues()
        cv.putNull(Campos.APOSTA_SEQUENCIA_ID.nome)
        cv.put(Campos.APOSTA_SEQUENCIA_SEQUENCIA.nome, sequencia)
        cv.put(Campos.APOSTA_SEQUENCIA_APOSTA.nome, aposta)

        return banco.use().insert(Campos.APOSTA_SEQUENCIA_TABLE.nome, null, cv)
    }

    fun save(aposta : Aposta){
        for(sequencia : Sequencia in aposta.sequencias){
            save(aposta.idAposta, sequencia.id_sequencia)
        }
    }


}