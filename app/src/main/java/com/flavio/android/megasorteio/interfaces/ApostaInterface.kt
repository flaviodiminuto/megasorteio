package com.flavio.android.megasorteio.interfaces

import com.flavio.android.megasorteio.model.Sequencia

interface ApostaInterface{
    fun adicionarSequencia(quantidade : Int, tamanho : Int) : Boolean
    fun adicionarSequencia(tamanho: Int) : Boolean
    fun adicionarSequencia(sequencia : Sequencia) : Boolean
    fun removerSequencia(sequencia : Sequencia) : Boolean
    fun alterarSequencia(novaSequencia: Sequencia, sequencias : ArrayList<Sequencia>, index : Int)
    fun sequenciasIguais(sequencia1 : Sequencia, sequencia2: Sequencia) : Boolean

}