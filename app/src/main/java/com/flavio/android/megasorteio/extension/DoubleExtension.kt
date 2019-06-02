package com.flavio.android.megasorteio.extension

import android.icu.text.DecimalFormat
import java.util.*

    fun Double.formataParaMoedaBrasileira() : String{
        val moedaBrasileira = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
        return moedaBrasileira.format(this)
                .replace("R$","R$ ")
                .replace("-R$", "R$ -")

}