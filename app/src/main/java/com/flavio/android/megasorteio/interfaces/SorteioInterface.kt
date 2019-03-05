package com.flavio.android.megasorteio.interfaces

import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia

interface SorteioInterface {
    fun validaSequenciaSorteada(): ArrayList<Int>
    fun verificaSorteio() : Boolean
}