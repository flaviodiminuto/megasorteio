package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.Sequencia
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class SequenciaTest {
    private var sequencia = Sequencia(4)

    @RepeatedTest(2)
    fun testGerarSequencia(){
        assertTrue(sequencia.gerarSequencia(2).tamanho ==6)
        assertTrue(sequencia.valor == 3.5)
    }

    @Test
    fun testTamanhoSequencia(){
        val sequencia1 = Sequencia(4)
        assertEquals(6,sequencia1.tamanho)

        val sequencia2 = Sequencia(7)
        assertEquals(7,sequencia2.tamanho)

        val sequencia3 = Sequencia(25)
        assertEquals(15,sequencia3.tamanho)
    }

    @Test
    fun testOrdenaNumeroSequencai(){
        val sequenciaDesordenada = arrayListOf<Int>(6,5,4,3,2,1)
        assertEquals(sequencia.ordenaNumerosSequencia(sequenciaDesordenada), arrayListOf<Int>(1,2,3,4,5,6))

    }

    @Test
    fun testaToString(){
        var sequencia = Sequencia(arrayListOf(1,2,3,4,5,6))
        assertEquals("1 2 3 4 5 6", sequencia.toString())
    }

    @Test
    fun testSetValor(){
        assertEquals(3.5,sequencia.setValor(6))
        assertEquals(24.5,sequencia.setValor(7))
        assertEquals(98.0,sequencia.setValor(8))
        assertEquals(294.0,sequencia.setValor(9))
        assertEquals(735.0,sequencia.setValor(10))
        assertEquals(1617.0,sequencia.setValor(11))
        assertEquals(3234.0,sequencia.setValor(12))
        assertEquals(6006.0,sequencia.setValor(13))
        assertEquals(10510.5,sequencia.setValor(14))
        assertEquals(17517.5,sequencia.setValor(15))
    }

}