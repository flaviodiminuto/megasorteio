package com.flavio.android.megasorteio.controller

import android.content.Context
import com.flavio.android.megasorteio.dao.ApostaDao
import com.flavio.android.megasorteio.dao.ApostaSequenciaDao
import com.flavio.android.megasorteio.dao.SequenciaDao
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia

class Controller(val context: Context) {

    fun salvarAposta(aposta: Aposta) = ApostaSequenciaDao(context).saveApostaSequencia(aposta)

    fun listarApostas(): MutableList<Aposta> {
        var ad = ApostaDao(context)
        return ad.listarTodasApostas()
    }
    fun lsitarApostasComSequencias(){
        //TODO --
    }
    fun pesquisarAposta(id : Long): Aposta {
        var ad = ApostaDao(context)
        return ad.consultarAposta(id)
    }
    fun pesquisarApostaComSequencia(id : Long) = ApostaSequenciaDao(context).consultarApostaSequencia(id)
    fun atualizarAposta(aposta: Aposta) = ApostaDao(context).atualizaAposta(aposta)
    fun atualizarSequencia(sequencia: Sequencia) = SequenciaDao(context).atualizarSequencia(sequencia)
}