package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.Aposta
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
        Assertions.assertEquals(true, aposta.adicionarSequencia(6))
        Assertions.assertEquals(true, aposta.adicionarSequencia(Sequencia(6)))
        Assertions.assertEquals(true, aposta.adicionarSequencia(20,100))
    }

    @Test
    fun testAdicionarSequenciaQuantidade(){

        Assertions.assertTrue(aposta.sequencias[2].numeros.size == tamanho && aposta.sequencias.size == quantidade)
        aposta.sequencias.clear()
        Assertions.assertTrue(aposta.adicionarSequencia(1,100))
        Assertions.assertTrue(aposta.adicionarSequencia(3,23))
        Assertions.assertTrue(aposta.adicionarSequencia(3,2))
        Assertions.assertTrue(aposta.sequencias[4].valor == 3.5 && aposta.sequencias[4].tamanho == 6)
        aposta.mostraTodasSequencias()
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
}