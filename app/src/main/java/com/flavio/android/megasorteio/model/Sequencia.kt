package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.SequenciaInterface
import java.util.Calendar
import java.util.Date


class Sequencia(var tamanho: Int) : SequenciaInterface  {

    constructor(numerosRecebidos : ArrayList<Int>):this( numerosRecebidos.size){
        numeros = numerosRecebidos }

    var id = Long
    var numeros = arrayListOf<Int>()
    var valor : Double
    var dataCriacao = Date()
    var dataAtualizacao = Date()
    val controlaNumero = ControlaNumero()
    private val LIMITE_INFERIOR = 6
    private val LIMITE_SUPERIOR = 15

    init{
        configuraTamanho()
        atribuiDataCriacao()
        gerarSequencia(tamanho)
        valor = setValor(tamanho)
    }

    private fun configuraTamanho() {
        tamanho = if (tamanho < LIMITE_INFERIOR)
            LIMITE_INFERIOR
        else if (tamanho > LIMITE_SUPERIOR)
            LIMITE_SUPERIOR else tamanho
    }

    override fun gerarSequencia(tamanho : Int): Sequencia{
        configuraTamanho()
        numeros = controlaNumero.preencheNumerosSequencia(tamanho)
        ordenaNumerosSequencia(numeros)
        return this
    }

    override fun ordenaNumerosSequencia(numeros : ArrayList<Int> ): ArrayList<Int>{
        numeros.sort()
        return numeros
    }

    override fun sequenciaUnica(sequencias : ArrayList<Sequencia>, sequencia : Sequencia): Boolean{
        for(sequenciaVerificada : Sequencia in sequencias) {
            when (sequencia.numeros) {
                sequenciaVerificada.numeros -> return false
            }
        }
        return true
    }

    override fun atribuiDataCriacao():Date{
        dataCriacao = atribuiDataAtualizacao()
        return dataCriacao
    }

    override fun atribuiDataAtualizacao() : Date{
        dataAtualizacao = Calendar.getInstance().time
       return dataAtualizacao
    }

    override fun setValor(tamanhoSequencia : Int) : Double{
        when(tamanhoSequencia){
            6 ->    return Preco.SEIS.tamanhoSequencia
            7 ->    return Preco.SETE.tamanhoSequencia
            8 ->    return Preco.OITO.tamanhoSequencia
            9 ->    return Preco.NOVE.tamanhoSequencia
            10 ->   return Preco.DEZ.tamanhoSequencia
            11 ->   return Preco.ONZE.tamanhoSequencia
            12 ->   return Preco.DOZE.tamanhoSequencia
            13 ->   return Preco.TREZE.tamanhoSequencia
            14 ->   return Preco.QUATORZE.tamanhoSequencia
            else -> return Preco.QUINZE.tamanhoSequencia
        }
    }

    override fun toString(): String {
        var retorno = String()
        for(numero  in numeros){
            retorno = "$retorno$numero "
        }
        return retorno.trimEnd()
    }
}