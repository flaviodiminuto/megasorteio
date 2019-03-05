package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.SorteioInterface
import java.time.Instant
import java.util.Date
import kotlin.collections.ArrayList

class Sorteio : SorteioInterface {

    var idSorteio = Long
    var apostas = ArrayList<Aposta>()
    var dataSorteio = Date()
    var sequenciaSorteada = Sequencia()
    var dataVerificacaoSorteio = Date()
    var apostasPremiadas = ArrayList<Aposta>()
    var sequenciasPremiadas = ArrayList<Sequencia>()
    var quantidadeAcertos = ArrayList<Long>()
    var erros = ArrayList<Int>()

    override fun validaSequenciaSorteada(): ArrayList<Int> {
        erros.clear()
        /* Uma sequencia sorteada será válida se nenhum erro for adicionado a lista */

        //Quantidade de digitos deve ser igual a 6
        if(sequenciaSorteada.tamanho != 6)
            if(!erros.contains(1)) erros.add(1) //<- código de erro para tamanho de sequencia errado

        //A sequencia deve ser ordenada
        sequenciaSorteada.ordenaNumerosSequencia()

        //Cada digito da sequencia deve ser único dentro da sequencia e estar dentro do intervalo correto
        for(digito : Int in sequenciaSorteada.numeros){
            var ocorrencia = 0
            for(numeroDaSequencia : Int in sequenciaSorteada.numeros){
                if(digito == numeroDaSequencia){
                    ocorrencia++
                    if(ocorrencia>1) {
                        if(!erros.contains(2)) erros.add(2) // <- código de erro para digito repetido
                    }
                }
                if (digito <1 || digito >60){
                    if(!erros.contains(3))  erros.add(3) // <- código de erro para digito fora do intervalo correto (1~60)
                }
            }
        }
        return erros
    }

    override fun verificaSorteio(): Boolean {
        var premiado  = false
        quantidadeAcertos = arrayListOf(0,0,0,0,0,0,0) //0,1,2,3,4,5 ou 6 acertos
        dataVerificacaoSorteio = Date.from(Instant.now())
        if(validaSequenciaSorteada().isEmpty()){
            for (aposta : Aposta in apostas){
                for(sequencia : Sequencia in aposta.sequencias){
                    when(aposta.numerosContidos(sequenciaSorteada,sequencia)){
                        0 -> quantidadeAcertos[0]++
                        1 -> quantidadeAcertos[1]++
                        2 -> quantidadeAcertos[2]++
                        3 -> quantidadeAcertos[3]++
                        4 -> {
                            quantidadeAcertos[4]++
                            if(!apostasPremiadas.contains(aposta)) apostasPremiadas.add(aposta)
                            if(!sequenciasPremiadas.contains(sequencia)) sequenciasPremiadas.add(sequencia)
                            premiado = true
                        }
                        5 -> {
                            quantidadeAcertos[5]++
                            if(!apostasPremiadas.contains(aposta)) apostasPremiadas.add(aposta)
                            if(!sequenciasPremiadas.contains(sequencia)) sequenciasPremiadas.add(sequencia)
                            premiado = true
                        }
                        6 -> {
                            quantidadeAcertos[6]++
                            if(!apostasPremiadas.contains(aposta)) apostasPremiadas.add(aposta)
                            if(!sequenciasPremiadas.contains(sequencia)) sequenciasPremiadas.add(sequencia)
                            premiado = true
                        }
                    }
                }
            }
        }
        return premiado
    }

}