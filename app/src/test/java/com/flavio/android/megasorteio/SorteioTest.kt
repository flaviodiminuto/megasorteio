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
        val numerosValidos = arrayListOf(1,2,3,4,5,6)
        val numerosTamanhoInvalido = arrayListOf(1,2,3,4,5,6,7)
        val numerosDigitosRepetidos = arrayListOf(1,1,2,3,4,5,6)
        val numerosIntervaloInvalido = arrayListOf(1,2,3,4,5,61)
        //Sequencia válida
        sorteio.sequenciaSorteada = Sequencia(numerosValidos)
        Assertions.assertTrue(sorteio.validaSequenciaSorteada().isEmpty())

        //Sequencia inválida (Tamanho incorreto)
        sorteio.sequenciaSorteada = Sequencia(numerosTamanhoInvalido)
        Assertions.assertTrue(sorteio.validaSequenciaSorteada().contains(1))

        //Sequencia inválida (Digito Repetido)
        sorteio.sequenciaSorteada = Sequencia(numerosDigitosRepetidos)
        Assertions.assertTrue(sorteio.validaSequenciaSorteada().contains(2))

        //Sequencia inválida (Digito fora do intervalo correto "1~60")
        sorteio.sequenciaSorteada = Sequencia(numerosIntervaloInvalido)
        Assertions.assertTrue(sorteio.validaSequenciaSorteada().contains(3))

    }

    @Test
    fun testVerificaSorteio(){
        val sequencias = arrayListOf(
                Sequencia(arrayListOf(1,2,3,4,5,6,7,8)),
                Sequencia(arrayListOf(2,3,4,5,6,7)),
                Sequencia(arrayListOf(1,2,3,4,5,6)),
                Sequencia(arrayListOf(1,2,3,4,5,6)),
                Sequencia(arrayListOf(10,11,12,13,14,15,16,17,18)),
                Sequencia(arrayListOf(20,21,22,23,24,25,26,27,28,29,30))
        )
        val sequenciaSorteada = Sequencia(arrayListOf(1,2,3,4,5,6))
        sorteio.apostas.first().sequencias =  sequencias
        sorteio.sequenciaSorteada = sequenciaSorteada
        Assertions.assertTrue(sorteio.verificaSorteio())
    }
}