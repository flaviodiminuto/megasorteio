package com.flavio.android.megasorteio.model

import java.time.LocalDateTime

class SorteioDTO {
    var idSorteio : Long = 0L
    var idAposta : Long = 0L
    var numeroSorteio : Long = 0L
    var dataVerificacaoSorteio = LocalDateTime.now()
    var quadra : Long = 0L
    var quina : Long = 0L
    var sena  : Long = 0L
    var maiorQuantidadeAcertos : Int = 0
    var numerosSorteados = mutableListOf<Int>()
    var numerosAcertados = mutableListOf<Int>()

}
