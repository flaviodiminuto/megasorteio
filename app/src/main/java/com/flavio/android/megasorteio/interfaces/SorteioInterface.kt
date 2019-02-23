package com.flavio.android.megasorteio.interfaces

interface SorteioInterface {
    fun gerarAposta(quantidade: Int): Boolean
    fun contabilizaAcertos()
    fun validaSequenciaSorteada(Sorteado: ArrayList<Int>): Boolean
    fun verificaSorteio() : Boolean
}