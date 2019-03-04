package com.flavio.android.megasorteio.interfaces

import com.flavio.android.megasorteio.model.Sequencia
import java.util. Date
import kotlin.collections.ArrayList

interface SequenciaInterface {
    fun configuraTamanho()
    fun gerarSequencia(tamanho: Int): Sequencia
    fun ordenaNumerosSequencia()
    fun ordenaNumerosSequencia(numeros : ArrayList<Int>) : ArrayList<Int>
    fun atribuiDataCriacao():Date
    fun atribuiDataAtualizacao() : Date
    fun setValor(tamanhoSequencia : Int)
    override fun toString(): String
}