package com.flavio.android.megasorteio.model

import java.time.LocalDateTime

class SorteioDTO {
    var idSorteio : Long = 0L
    var idAposta : Long = 0L
    var numeroSorteio : Long = 0L
    var dataVerificacaoSorteio = LocalDateTime.now()
    var quadra : Int = 0
    var quina : Int = 0
    var sena  : Int = 0
    var maiorQuantidadeAcertos : Int = 0
    var idSequenciaComMaisAcertos : Long =0
    var numerosSorteados = mutableListOf<Int>()
    var numerosAcertados = mutableListOf<Int>()

}
