package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.enumeradores.TamanhoSequencia
import com.flavio.android.megasorteio.model.Sequencia
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ApostaTeste {
    var aposta = Aposta()
    var tamanho : Int = 6
    var quantidade : Int =3
    init {
        aposta.adicionarSequencia(3,6)
    }


   @Test
    fun testAdicionarSequencia(){
        Assertions.assertTrue( aposta.adicionarSequencia(6))
        Assertions.assertTrue( aposta.adicionarSequencia(Sequencia(6)))
        Assertions.assertTrue( aposta.adicionarSequencia(20,100))
        Assertions.assertTrue( aposta.adicionarSequencia(arrayListOf(1,2,3,4,5,6)))
    }

    @Test
    fun testAdicionarSequenciaQuantidade(){

        Assertions.assertTrue(aposta.sequencias[2].numeros.size == tamanho && aposta.sequencias.size == quantidade)
        aposta.sequencias.clear()
        Assertions.assertTrue(aposta.adicionarSequencia(1,100))
        Assertions.assertTrue(aposta.adicionarSequencia(3,23))
        Assertions.assertTrue(aposta.adicionarSequencia(3,2))
        Assertions.assertTrue(aposta.sequencias[4].valor == 3.5 && aposta.sequencias[4].tamanho == 6)
        Assertions.assertTrue(aposta.sequencias[3].tamanho == 15)
        Assertions.assertTrue(aposta.sequencias[3].valor == 17517.5)
    }

    @Test
    fun testRemoversequencia(){
        var sequenciaParaRemover = aposta.sequencias[2]
        Assertions.assertTrue(aposta.removerSequencia(sequenciaParaRemover))
    }

    @Test
    fun testAlterarSequencia(){
        var sequenciaParaAlterar = Sequencia()
        sequenciaParaAlterar.numeros = arrayListOf(1,2,3,4,5,6)
        aposta.alterarSequencia(sequenciaParaAlterar,aposta.sequencias,0)
        Assertions.assertTrue(aposta.sequencias.first().numeros == arrayListOf(1,2,3,4,5,6))
    }

    @Test
    fun testSequenciaExistente(){
        var aposta2 = Aposta()
        var sequencia1 = Sequencia(arrayListOf(1,2,3,4,5,6)) //sequencia contida
        var sequencia2 = Sequencia(arrayListOf(12,14,2,5,6,9)) //sequencia contida
        var sequencia3 = Sequencia(arrayListOf(0,1,2,3,4,5)) // sequencia nao contida
        var sequencia4 = Sequencia(arrayListOf(1,3,5,8,9,21)) // sequencia nao contida

        aposta2.adicionarSequencia(arrayListOf(1,2,3,4,5,6))
        aposta2.adicionarSequencia(arrayListOf(2,3,4,5,6,7))
        aposta2.adicionarSequencia(arrayListOf(2,3,4,5,6,7,8,9))
        aposta2.adicionarSequencia(arrayListOf(2,3,4,5,6,7,8,9,10,11,12,13,14))

        Assertions.assertTrue(aposta2.sequenciaExistente(sequencia1))
        Assertions.assertTrue(aposta2.sequenciaExistente(sequencia2))
        Assertions.assertFalse(aposta2.sequenciaExistente(sequencia3))
        Assertions.assertFalse(aposta2.sequenciaExistente(sequencia4))
    }

    @Test
    fun testNumerosContidos(){
        var aposta2 = Aposta()
        aposta2.adicionarSequencia(Sequencia(arrayListOf(1,2,3,4,5,6)))
        aposta2.adicionarSequencia(Sequencia(arrayListOf(2,3,4,5,6,7)))
        aposta2.adicionarSequencia(Sequencia(arrayListOf(1,1,2,3,4,5)))

        Assertions.assertTrue(aposta2.quantosNumerosContidos(Sequencia(arrayListOf(1,2,3,4,5,6)),aposta2.sequencias[0])==6)
        Assertions.assertFalse(aposta2.quantosNumerosContidos(Sequencia(arrayListOf(1,2,3,4,5,6)),aposta2.sequencias[1])==6)
    }

    @Test
    fun testSetValor(){
        var aposta2 = Aposta()
        aposta2.adicionarSequencia(10,6)
        Assertions.assertTrue(aposta2.valor == (10* TamanhoSequencia.SEIS.preco))

        aposta2.adicionarSequencia(15)
        Assertions.assertTrue(aposta2.valor == (TamanhoSequencia.QUINZE.preco+ 10* TamanhoSequencia.SEIS.preco))
    }

    @Test
    fun testMostraTodasSequencias(){
        var aposta2 = Aposta()
        val message = "[[1, 2, 3, 4, 5, 6], [2, 3, 4, 5, 6, 7], [1, 1, 2, 3, 4, 5]]"
        aposta2.adicionarSequencia(Sequencia(arrayListOf(1,2,3,4,5,6)))
        aposta2.adicionarSequencia(Sequencia(arrayListOf(2,3,4,5,6,7)))
        aposta2.adicionarSequencia(Sequencia(arrayListOf(1,1,2,3,4,5)))

        Assertions.assertEquals(message, aposta2.mostraTodasSequencias())
    }

    @Test
    fun testToSting(){
        var aposta2 = Aposta()
        val message = "[1, 2, 3, 4, 5, 6], [2, 3, 4, 5, 6, 7], [1, 1, 2, 3, 4, 5]"
        aposta2.adicionarSequencia(Sequencia(arrayListOf(1,2,3,4,5,6)))
        aposta2.adicionarSequencia(Sequencia(arrayListOf(2,3,4,5,6,7)))
        aposta2.adicionarSequencia(Sequencia(arrayListOf(1,1,2,3,4,5)))

        Assertions.assertEquals(message, aposta2.toString())
    }
}