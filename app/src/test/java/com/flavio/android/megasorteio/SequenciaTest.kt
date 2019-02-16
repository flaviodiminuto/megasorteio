package com.flavio.android.megasorteio

import com.flavio.android.megasorteio.model.Sequencia
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class SequenciaTest {
    var sequencia = Sequencia(6)

    @RepeatedTest(5000)
    fun testGeraNumero(){
        assertTrue(sequencia.geraNumero() >= 1 && sequencia.geraNumero() <= 60)

    }
}