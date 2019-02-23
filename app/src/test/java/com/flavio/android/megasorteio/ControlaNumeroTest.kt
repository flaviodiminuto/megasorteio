package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.ControlaNumero
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ControlaNumeroTest(){
var numero = ControlaNumero()

    @RepeatedTest(10)
    fun testGeraNumero(){
        Assertions.assertTrue(numero.geraNumero() >= 1 && numero.geraNumero() <= 60)

    }

    @RepeatedTest(10)
    fun testaGerarNumerosSequencia(){
        assert(numero.preencheNumerosSequencia(6).isNotEmpty())
    }

    @Test
    fun testaNumeroUnico(){
        val numeros : List<Int> = listOf(1,2,3,4,5,6,7)
        val procurado1 = 3
        val procurado2 = 5
        val procurado3 = 8

        assertAll("testa numeros unicos",
                {Assertions.assertFalse(numero.numeroUnico(numeros,procurado1))},
                {Assertions.assertFalse(numero.numeroUnico(numeros,procurado2))},
                {Assertions.assertTrue(numero.numeroUnico(numeros,procurado3))})
    }

}