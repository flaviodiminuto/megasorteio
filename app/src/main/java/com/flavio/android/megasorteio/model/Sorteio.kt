package com.flavio.android.megasorteio.model

import com.flavio.android.megasorteio.interfaces.SorteioInterface
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


    override fun validaSequenciaSorteada(sequenciaSorteada : Sequencia): Boolean {
        /*
        Uma sequencia sorteada Válida deve
            ter 6 digitos
            estar ordenada para verificação
            não ter numeros repetidos
            todos os digitos devem ter o valor de 1 a 60
         */
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun verificaSorteio(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun contabilizaAcertos() : Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}