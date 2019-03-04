package com.flavio.android.megasorteio.interfaces

import com.flavio.android.megasorteio.model.Sequencia

interface SorteioInterface {
    fun validaSequenciaSorteada(sequenciaSorteada : Sequencia): Boolean
    fun verificaSorteio() : Boolean
    fun contabilizaAcertos() : Long
}