package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ApostaTeste {
    var aposta = Aposta()
    @Test
    fun testaAdicionarSequencia(){
        Assertions.assertEquals(true, aposta.adicionarSequencia(6))
        Assertions.assertEquals(true, aposta.adicionarSequencia(Sequencia(6)))
        Assertions.assertEquals(true, aposta.adicionarSequencia(20,100))
        Assertions.assertEquals(true, aposta.adicionarSequencia(1,100))
        Assertions.assertEquals(true, aposta.adicionarSequencia(8,100))


    }
}