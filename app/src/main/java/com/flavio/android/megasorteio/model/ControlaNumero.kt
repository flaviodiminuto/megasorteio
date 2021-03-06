package com.flavio.android.megasorteio.model

import java.io.Serializable
import java.util.concurrent.ThreadLocalRandom

class ControlaNumero: Serializable {

    fun preencheNumerosSequencia( tamanho: Int):ArrayList<Int>{
        var numeros = arrayListOf<Int>()
        var gerado :Int
        for(numero : Int in 0 until tamanho) {
            do {
                gerado = geraNumero()
            } while (!numeroUnico(numeros, gerado))
            numeros.add(gerado)
        }
        return numeros
    }

    fun geraNumero():Int{
        return ThreadLocalRandom.current().nextInt(1,61)
    }

    fun numeroUnico(sequencia : List<Int>,numero : Int):Boolean{
        return !sequencia.contains(numero)
    }

    fun numerosToMyString(sequencia: MutableList<Int>): String {
        var numeros = "|"
        for (numero in sequencia) {
            numeros += "$numero|"
        }
        return numeros
    }
}