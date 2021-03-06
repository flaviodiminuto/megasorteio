package com.flavio.android.megasorteio.controller

import android.content.Context
import com.flavio.android.megasorteio.dao.ApostaDao
import com.flavio.android.megasorteio.dao.ApostaSequenciaDao
import com.flavio.android.megasorteio.dao.SequenciaDao
import com.flavio.android.megasorteio.dao.SorteioDao
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia
import com.flavio.android.megasorteio.model.SorteioDTO

class Controller(val context: Context) {

    fun salvarAposta(aposta: Aposta) = ApostaSequenciaDao(context).saveApostaSequencia(aposta)
    fun listarApostas(): MutableList<Aposta>  = ApostaDao(context).listarTodasApostas()
    fun consultarAposta(id : Long): Aposta  = ApostaDao(context).consultarAposta(id)
    fun pesquisarApostaComSequencia(id : Long) = ApostaSequenciaDao(context).consultarApostaSequencia(id)
    fun atualizarAposta(aposta: Aposta) = ApostaDao(context).atualizaAposta(aposta)
    fun atualizarSequencia(sequencia: Sequencia) = SequenciaDao(context).atualizarSequencia(sequencia)
    fun deletarAposta(aposta: Aposta) = ApostaDao(context).deletarAposta(aposta)
    fun deletarSequencia(sequencia: Sequencia) = SequenciaDao(context).deletarSequencia(sequencia)
    fun deletarApostaSequencia(aposta: Aposta) = ApostaSequenciaDao(context).deletarApostaSequencia(aposta)
    fun verificarSorteio(numeros : MutableList<Int>) = ApostaSequenciaDao(context).verificarSorteio(numeros)
    fun atualizarOuSalvarSorteio(sorteio : SorteioDTO) = if(sorteio.idAposta<1) SorteioDao(context).salvar(sorteio) else SorteioDao(context).atualizar(sorteio)
    fun consultarSequenciasFixas() = SequenciaDao(context).consultarSequenciasFixas()
}