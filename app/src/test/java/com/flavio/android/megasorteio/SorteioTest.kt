package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia
import com.flavio.android.megasorteio.model.Sorteio
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SorteioTest {
    var sorteio = Sorteio()

    init{
        sorteio.apostas.add(Aposta())
        sorteio.apostas.first().adicionarSequencia(10,6)
    }

    @Test
    fun testValidaSequenciaSorteada(){
        val numeros = arrayListOf<Int>(1,2,3,4,5,6,7)
        var sequenciaSorteada = Sequencia(numeros)
        val numerosInvalidos = arrayListOf<Int>(1,1,2,3,4,5,6)
        var sequenciaInvalida = Sequencia(numerosInvalidos)

        Assertions.assertTrue(sorteio.validaSequenciaSorteada(sequenciaSorteada))
        Assertions.assertFalse(sorteio.validaSequenciaSorteada(sequenciaInvalida))
    }

    @Test
    fun testVerificaSorteio(){
        val sequencias = arrayListOf(
                arrayListOf(1,2,3,4,5,6,7,8),
                arrayListOf(2,3,4,5,6,7),
                arrayListOf(1,2,3,4,5,6),
                arrayListOf(1,2,3,4,5,6),
                arrayListOf(10,11,12,13,14,15,16,17,18),
                arrayListOf(20,21,22,23,24,25,26,27,28,29,30)
        )
        sorteio.apostas.first().sequencias.clear()
        Assertions.assertTrue(sorteio.verificaSorteio())
    }

    @Test
    fun testContabilizaAcertos(){

    }
}