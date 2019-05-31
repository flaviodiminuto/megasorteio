package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.enumeradores.TamanhoSequencia
import com.flavio.android.megasorteio.interfaces.SequenciaInterface
import java.io.Serializable
import java.util.*


class Sequencia(var tamanho: Int) : SequenciaInterface, Serializable {

    constructor() : this(6)

    constructor(numerosRecebidos : ArrayList<Int>):this( numerosRecebidos.size){ numeros = numerosRecebidos }
    var id_sequencia : Long = 0L
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
            6 ->    valor = TamanhoSequencia.SEIS.preco
            7 ->    valor = TamanhoSequencia.SETE.preco
            8 ->    valor = TamanhoSequencia.OITO.preco
            9 ->    valor = TamanhoSequencia.NOVE.preco
            10 ->   valor = TamanhoSequencia.DEZ.preco
            11 ->   valor = TamanhoSequencia.ONZE.preco
            12 ->   valor = TamanhoSequencia.DOZE.preco
            13 ->   valor = TamanhoSequencia.TREZE.preco
            14 ->   valor = TamanhoSequencia.QUATORZE.preco
            else -> valor = TamanhoSequencia.QUINZE.preco
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