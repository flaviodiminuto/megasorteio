package com.flavio.android.megasorteio.controller

import android.content.Context
import com.flavio.android.megasorteio.dao.ApostaDao
import com.flavio.android.megasorteio.dao.ApostaSequenciaDao
import com.flavio.android.megasorteio.dao.SequenciaDao
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia

class ControlaAposta(val context: Context) {

    fun salvar(aposta: Aposta) : Long {
        var ad = ApostaDao(context)
        var sd = SequenciaDao(context)
        var asd = ApostaSequenciaDao(context)

        aposta.idAposta = ad.salvar(aposta)
        if(aposta.idAposta >= 0){
            for(sequencia : Sequencia in aposta.sequencias){
                sequencia.id_sequencia = sd.save(sequencia)
                asd.save(aposta.idAposta, sequencia.id_sequencia)
            }
        }
        return aposta.idAposta
    }
    fun listaTodosSemSequencias(): MutableList<Aposta> {
        var ad = ApostaDao(context)
        return ad.listarTodosSemSequencia()
    }
}