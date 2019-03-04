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
        assertEquals("[1, 2, 3, 4, 5, 6]", sequencia.toString())
    }

    @Test
    fun testSetValor(){
        var sequencia6 = Sequencia(6)
        var sequencia7 = Sequencia(7)
        var sequencia8 = Sequencia(8)
        var sequencia9 = Sequencia(9)
        var sequencia10 = Sequencia(10)
        var sequencia11 = Sequencia(11)
        var sequencia12 = Sequencia(12)
        var sequencia13 = Sequencia(13)
        var sequencia14 = Sequencia(14)
        var sequencia15 = Sequencia(15)
        assertEquals(3.5,sequencia6.valor)
        assertEquals(24.5,sequencia7.valor)
        assertEquals(98.0,sequencia8.valor)
        assertEquals(294.0,sequencia9.valor)
        assertEquals(735.0,sequencia10.valor)
        assertEquals(1617.0,sequencia11.valor)
        assertEquals(3234.0,sequencia12.valor)
        assertEquals(6006.0,sequencia13.valor)
        assertEquals(10510.5,sequencia14.valor)
        assertEquals(17517.5,sequencia15.valor)
    }

}