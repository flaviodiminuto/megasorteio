package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.ApostaInterface

class Aposta : ApostaInterface  {
    var idAposta = Long
    var sequencias = ArrayList<Sequencia>()
    var valor = Double

    override fun adicionarSequencia(quantidade: Int, tamanho: Int) : Boolean {
        for(item : Int in 1..quantidade){
            sequencias.add(Sequencia(tamanho))
        }
        if(!sequencias.isEmpty()) {
            setValor()
            return true
        }
        return false
    }

    override fun adicionarSequencia(tamanho: Int): Boolean {
        return adicionarSequencia(1,tamanho)
    }

    override fun adicionarSequencia(sequencia: Sequencia): Boolean {
        return sequencias.add(sequencia)
    }

    override fun removerSequencia(sequencia : Sequencia): Boolean {
        return sequencias.remove(sequencia)
    }

    override fun alterarSequencia(novaSequencia: Sequencia, sequencias : ArrayList<Sequencia>, index : Int){
        sequencias[index] = novaSequencia
    }

    override fun sequenciasIguais(sequencia1: Sequencia, sequencia2: Sequencia): Boolean {
        if(sequencia1.numeros == sequencia2.numeros){
            return true
        }
        return false
    }
    fun setValor(){
        var valor : Double = 0.0
        for (sequencia : Sequencia in sequencias){
            valor.plus(sequencia.valor)
        }
        this.valor.apply { valor }
    }
}