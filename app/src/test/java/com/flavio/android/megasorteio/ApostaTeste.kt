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
        Assertions.assertTrue(aposta.adicionarSequencia(1,100))
        Assertions.assertTrue(aposta.adicionarSequencia(3,23))
        Assertions.assertTrue(aposta.adicionarSequencia(3,2))
        Assertions.assertTrue(aposta.sequencias[9].valor == 3.5 && aposta.sequencias[9].tamanho == 6)
        Assertions.assertTrue(aposta.sequencias[3].valor == 17517.5 && aposta.sequencias[3].tamanho ==15 )
    }

    @Test
    fun testRemoversequencia(){
        var sequenciaParaRemover = aposta.sequencias[2]
        Assertions.assertTrue(aposta.removerSequencia(sequenciaParaRemover))
    }

    @Test
    fun testAlterarSequencia(){
        var sequenciaParaAlterar = aposta.sequencias[2]
        aposta.alterarSequencia(sequenciaParaAlterar,aposta.sequencias,1)
        Assertions.assertTrue(aposta.sequenciasIguais(aposta.sequencias[1],aposta.sequencias[2]))
    }
}