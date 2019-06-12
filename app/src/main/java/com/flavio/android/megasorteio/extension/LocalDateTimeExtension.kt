package com.flavio.android.megasorteio.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

    fun LocalDateTime.toMyString(): String{
        val formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return this.format(formater)
    }