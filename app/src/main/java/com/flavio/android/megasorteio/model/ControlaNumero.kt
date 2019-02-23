package com.flavio.android.megasorteio.model

import java.util.concurrent.ThreadLocalRandom

class ControlaNumero {

    fun preencheNumerosSequencia( tamanho: Int):ArrayList<Int>{
        var numeros = arrayListOf<Int>()
        var gerado :Int
        for(numero : Int in 0..tamanho) {
            do {
                gerado = geraNumero()
            } while (!numeroUnico(numeros, gerado))
            numeros.add(gerado)
        }
        return numeros;
    }

    fun geraNumero():Int{
        return ThreadLocalRandom.current().nextInt(1,61)
    }

    fun numeroUnico(sequencia : List<Int>,numero : Int):Boolean{
        return !sequencia.contains(numero)
    }


}