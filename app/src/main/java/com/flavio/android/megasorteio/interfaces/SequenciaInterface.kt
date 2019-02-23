package com.flavio.android.megasorteio.interfaces

import com.flavio.android.megasorteio.model.Sequencia
import java.util. Date
import kotlin.collections.ArrayList

interface SequenciaInterface {
    fun gerarSequencia(tamanho: Int): Sequencia
    fun ordenaNumerosSequencia(numeros : ArrayList<Int>) : ArrayList<Int>
    fun sequenciaUnica(sequencias : ArrayList<Sequencia>, sequencia: Sequencia) : Boolean
    fun atribuiDataCriacao():Date
    fun atribuiDataAtualizacao() : Date
    fun setValor(tamanhoSequencia : Int):Double
    override fun toString(): String
}