package com.flavio.android.megasorteio.model

import java.util.concurrent.ThreadLocalRandom

class Sequencia(val tamanho: Long) {
    var numeros = listOf<Int>()

    fun geraNumero():Int{
        return ThreadLocalRandom.current().nextInt(1,61)
    }

}