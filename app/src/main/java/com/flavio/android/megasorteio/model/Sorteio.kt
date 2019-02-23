package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.SorteioInterface
import java.util.*
import kotlin.collections.ArrayList

class Sorteio : SorteioInterface {
    var idSorteio = Int
    var apostas = ArrayList<Aposta>()
    var dataSorteio = Date()
    var apostasPremiadas = ArrayList<Aposta>()
    var sequenciasPremiadas = ArrayList<Sequencia>()
    var quantidadeAcertos = ArrayList<Int>()

    override fun gerarAposta(quantidade: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contabilizaAcertos() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validaSequenciaSorteada(Sorteado: ArrayList<Int>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun verificaSorteio(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}