package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.Sequencia
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class SequenciaTest {
    var sequencia = Sequencia(4)

    @RepeatedTest(10)
    fun testGerarSequencia(){
        assertNotEquals(sequencia.gerarSequencia(),sequencia.gerarSequencia())
    }
    @Test
    fun testTamanhoSequencia(){
        val sequencia1 = Sequencia(4)
        assertEquals(5,sequencia1.tamanho)

        val sequencia2 = Sequencia(7)
        assertEquals(7,sequencia2.tamanho)

        val sequencia3 = Sequencia(15)
        assertEquals(14,sequencia3.tamanho)

    }

    @Test
    fun testOrdenaNumeroSequencai(){
        val sequenciaDesordenada = arrayListOf<Int>(6,5,4,3,2,1)
        assertEquals(sequencia.ordenaNumerosSequencia(sequenciaDesordenada), arrayListOf<Int>(1,2,3,4,5,6))

    }
    @RepeatedTest(10)
    fun testaSequenciaUnica(){
        val quantidadeSequencias = 3
        var sequenciaInicial = arrayListOf<Sequencia>()
        for(i in 0..quantidadeSequencias) {
            sequenciaInicial.add(Sequencia(6))
        }
        assertNotEquals(sequenciaInicial.toString(),sequencia.sequenciaUnica(sequenciaInicial).toString())
    }

    @Test
    fun testaToString(){
        var sequencia = Sequencia(arrayListOf(1,2,3,4,5,6))
        assertEquals("1 2 3 4 5 6", sequencia.toString())
    }

}