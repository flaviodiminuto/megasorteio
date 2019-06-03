package com.flavio.android.megasorteio.extension

import android.icu.text.DecimalFormat

    fun Double.formataParaMoedaBrasileira() : String{
        val moeda = DecimalFormat("###,###,###,#00.00")
        return "R$ ${moeda.format(this)}"



}