package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.SequenciaInterface
import java.util.Calendar
import java.util.Date


class Sequencia(var tamanho: Int) : SequenciaInterface  {

    constructor() : this(6)

    constructor(numerosRecebidos : ArrayList<Int>):this( numerosRecebidos.size){ numeros = numerosRecebidos }

    var numeros = arrayListOf<Int>()
    var valor = 0.0
    var dataCriacao = Date()
    var dataAtualizacao = Date()
    val controlaNumero = ControlaNumero()
    private val LIMITE_INFERIOR = 6
    private val LIMITE_SUPERIOR = 15

    init{
        configuraTamanho()
        atribuiDataCriacao()
        gerarSequencia(tamanho)
        setValor(tamanho)
    }

    override fun configuraTamanho() {
        tamanho = if (tamanho < LIMITE_INFERIOR)
            LIMITE_INFERIOR
        else if (tamanho > LIMITE_SUPERIOR)
            LIMITE_SUPERIOR else tamanho
    }

    override fun gerarSequencia(tamanho : Int): Sequencia{
        configuraTamanho()
        numeros = controlaNumero.preencheNumerosSequencia(tamanho)
        ordenaNumerosSequencia()
        return this
    }

    override fun ordenaNumerosSequencia(){
        this.numeros.sort()
    }
    override fun ordenaNumerosSequencia(numeros : ArrayList<Int> ): ArrayList<Int>{
        numeros.sort()
        return numeros
    }

    override fun atribuiDataCriacao():Date{
        dataCriacao = atribuiDataAtualizacao()
        return dataCriacao
    }

    override fun atribuiDataAtualizacao() : Date{
        dataAtualizacao = Calendar.getInstance().time
       return dataAtualizacao
    }

    override fun setValor(tamanhoSequencia : Int){
        when(tamanhoSequencia){
            6 ->    valor = Preco.SEIS.tamanhoSequencia
            7 ->    valor = Preco.SETE.tamanhoSequencia
            8 ->    valor = Preco.OITO.tamanhoSequencia
            9 ->    valor = Preco.NOVE.tamanhoSequencia
            10 ->   valor = Preco.DEZ.tamanhoSequencia
            11 ->   valor = Preco.ONZE.tamanhoSequencia
            12 ->   valor = Preco.DOZE.tamanhoSequencia
            13 ->   valor = Preco.TREZE.tamanhoSequencia
            14 ->   valor = Preco.QUATORZE.tamanhoSequencia
            else -> valor = Preco.QUINZE.tamanhoSequencia
        }
    }

    override fun toString(): String {
        var retorno = String()
        for(numero  in numeros){
            retorno = "$retorno$numero, "
        }
        return "[${retorno.removeRange(retorno.length-2,retorno.length-0)}]"
    }
}