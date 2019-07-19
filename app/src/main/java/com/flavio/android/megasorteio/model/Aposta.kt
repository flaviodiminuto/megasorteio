package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.ApostaInterface
import java.io.Serializable

class Aposta : ApostaInterface , Serializable {
    var idAposta: Long = 0L
    var sequencias = mutableListOf<Sequencia>()
    var quantidadeSequencias : Int = 0
    var valor : Double = 0.0

    override fun adicionarSequencia(quantidade: Int, tamanho: Int) : Boolean {
        var sequencia = Sequencia(tamanho)
        for(item : Int in 1..quantidade){
            while(sequenciaExistente(sequencia)){
               sequencia = Sequencia(tamanho)
            }
            quantidadeSequencias++
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

    fun adicionarSequenciaList(sequencias : MutableList<Sequencia>): Boolean{
        if(this.sequencias.addAll(sequencias)) {
            quantidadeSequencias = this.sequencias.size
            setValor()
            return true
        }
        return false
    }

    override fun adicionarSequencia(numeros: ArrayList<Int>): Boolean {
        var sequencia = Sequencia(numeros.size)
        sequencia.numeros = sequencia.ordenaNumerosSequencia(numeros)
        return sequencias.add(sequencia)
    }

    override fun removerSequencia(sequencia : Sequencia): Boolean {
        return sequencias.remove(sequencia)
        quantidadeSequencias = this.sequencias.size
        setValor()
    }

    override fun alterarSequencia(novaSequencia: Sequencia, sequencias : MutableList<Sequencia>, index : Int){
        sequencias[index] = novaSequencia
    }

    override fun sequenciaExistente(sequenciaVerificada: Sequencia): Boolean {
        /* VERIFICANDO SEQUENCIA
            Não será unica se
                 a sequencia verificada estiver contida em alguma sequencia da aposta
                 a sequencia estará contida se todos os digitos da sequencia de tamanho menor ou igual estiver totalmente contida na sequencia maior*/
        sequenciaVerificada.ordenaNumerosSequencia()
        for(sequencia : Sequencia in sequencias){
            if(sequenciaVerificada.tamanho == quantosNumerosContidos(sequenciaVerificada,sequencia)) {
                return true
            }
        }
        return false
    }

    override fun quantosNumerosContidos(sequenciaVerificada: Sequencia, sequenciaContainer: Sequencia): Int {
        var ocorrencias = 0
        if(sequenciaVerificada.tamanho > sequenciaContainer.tamanho) return quantosNumerosContidos(sequenciaContainer,sequenciaVerificada)

        for(digitoVerificado : Int in sequenciaVerificada.numeros){
            for(digitoContainer : Int in sequenciaContainer.numeros){
                if(digitoVerificado>digitoContainer) continue
                else if(digitoVerificado==digitoContainer) ocorrencias++
            }
        }
        return ocorrencias
    }
    fun numerosContidos(sequenciaVerificada: Sequencia, sequenciaContainer: Sequencia): MutableList<Int> {
        var numerosContidos = mutableListOf<Int>()
        if(sequenciaVerificada.tamanho > sequenciaContainer.tamanho) return numerosContidos(sequenciaContainer,sequenciaVerificada)

        for(digitoVerificado : Int in sequenciaVerificada.numeros){
            for(digitoContainer : Int in sequenciaContainer.numeros){
                if(digitoVerificado>digitoContainer) continue
                else if(digitoVerificado==digitoContainer) numerosContidos.add(digitoVerificado)
            }
        }
        return numerosContidos
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
        return if(retorno == "")
            "Aposta vazia."
        else
            "${retorno.removeRange(retorno.length-2,retorno.length)}"
    }

    /**
     * Retorna uma sequencia atraves do id
     * @return sequencia com id 0L (zero) caso nao encontre  a sequencia
     */
    fun getSequencia(id : Long): Sequencia{
        for(sequencia in sequencias){
            if(sequencia.idSequencia == id) return sequencia
        }
        return Sequencia()
    }

    fun removeSequenciasFixasDuplicadas(qtdSequenciasFixas: Int): Aposta{
        val fixas= mutableListOf<Sequencia>()
        if(this.sequencias.isNotEmpty()){
            fixas.addAll(this.sequencias.subList(0,qtdSequenciasFixas-1))
        }
        for(sequencia: Sequencia in fixas) {
            for (i: Int in qtdSequenciasFixas until this.sequencias.size) {
                if(this.sequencias[i].idSequencia == sequencia.idSequencia){
                    this.sequencias.remove(this.sequencias[i])
                }
            }
        }
        return this
    }
}