package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.ApostaInterface

class Aposta : ApostaInterface  {
    var idAposta = Long
    var sequencias = ArrayList<Sequencia>()
    var valor = 0.0

    override fun adicionarSequencia(quantidade: Int, tamanho: Int) : Boolean {
        var sequencia = Sequencia(tamanho)
        for(item : Int in 1..quantidade){
            while(sequenciaExistente(sequencia)){
               sequencia = Sequencia(tamanho)
            }
            sequencias.add(sequencia)
        }
        if(!sequencias.isEmpty()) {
            setValor()
            return true
        }
        valor = 0.0
        return false
    }

    override fun adicionarSequencia(tamanho: Int): Boolean {
        return adicionarSequencia(1,tamanho)
    }

    override fun adicionarSequencia(sequencia: Sequencia): Boolean {
        return sequencias.add(sequencia)
    }

    override fun adicionarSequencia(numeros: ArrayList<Int>): Boolean {
        var sequencia = Sequencia(numeros.size)
        sequencia.numeros = sequencia.ordenaNumerosSequencia(numeros)
        return sequencias.add(sequencia)
    }

    override fun removerSequencia(sequencia : Sequencia): Boolean {
        return sequencias.remove(sequencia)
    }

    override fun alterarSequencia(novaSequencia: Sequencia, sequencias : ArrayList<Sequencia>, index : Int){
        sequencias[index] = novaSequencia
    }

    override fun sequenciaExistente(sequenciaVerificada: Sequencia): Boolean {
        /* VERIFICANDO SEQUENCIA
            Não será unica se
                 a sequencia verificada estiver contida em alguma sequencia da aposta
                 a sequencia estará contida se todos os digitos da sequencia de tamanho menor ou igual estiver totalmente contida na sequencia maior*/
        sequenciaVerificada.ordenaNumerosSequencia()
        for(sequencia : Sequencia in sequencias){
            if(sequenciaVerificada.tamanho == numerosContidos(sequenciaVerificada,sequencia)) {
                return true
            }
        }
        return false
    }

    override fun numerosContidos(sequenciaVerificada: Sequencia, sequenciaContainer: Sequencia): Int {
        var ocorrencias = 0
        if(sequenciaVerificada.tamanho > sequenciaContainer.tamanho) return numerosContidos(sequenciaContainer,sequenciaVerificada)

        for(digitoVerificado : Int in sequenciaVerificada.numeros){
            for(digitoContainer : Int in sequenciaContainer.numeros){
                if(digitoVerificado>digitoContainer) continue
                else if(digitoVerificado==digitoContainer) ocorrencias++
            }
        }
        return ocorrencias
    }

    override fun setValor(){
        valor = 0.0
        for (sequencia : Sequencia in sequencias){
           valor  = valor.plus(sequencia.valor)
        }
    }

    override fun mostraTodasSequencias()  : String{
        var message = ""
        for (sequencia: Sequencia in sequencias)
            message = "$message$sequencia, "
        message ="[$message]"
        return message.removeRange(message.length-3,message.length-1)
    }

    override fun toString(): String {
        var retorno = ""
        for(sequencia : Sequencia in sequencias){ retorno  = "$retorno$sequencia, "}
        return "[${retorno.removeRange(retorno.length-2,retorno.length-0)}]"
    }
}