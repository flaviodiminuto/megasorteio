package com.flavio.android.megasorteio.interfaces

import com.flavio.android.megasorteio.model.Sequencia

interface ApostaInterface{
    fun adicionarSequencia(quantidade : Int, tamanho : Int) : Boolean
    fun adicionarSequencia(tamanho: Int) : Boolean
    fun adicionarSequencia(sequencia : Sequencia) : Boolean
    fun adicionarSequencia(numeros : ArrayList<Int>):Boolean
    fun removerSequencia(sequencia : Sequencia) : Boolean
    fun alterarSequencia(novaSequencia: Sequencia, sequencias : ArrayList<Sequencia>, index : Int)
    fun sequenciaExistente(sequenciaVerificada : Sequencia): Boolean
    fun numerosContidos(sequenciaVerificada: Sequencia, sequenciaContainer : Sequencia) : Int
    fun setValor()
    fun mostraTodasSequencias() : String
    override fun toString(): String

}