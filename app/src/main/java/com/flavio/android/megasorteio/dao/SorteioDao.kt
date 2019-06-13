package com.flavio.android.megasorteio.dao

import android.content.ContentValues
import android.content.Context
import com.flavio.android.megasorteio.database.Banco
import com.flavio.android.megasorteio.enumeradores.Campos
import com.flavio.android.megasorteio.extension.toMyString
import com.flavio.android.megasorteio.model.ControlaNumero
import com.flavio.android.megasorteio.model.SorteioDTO

class SorteioDao(context : Context) {
    private val banco = Banco(context)
    private val controlaNumeros = ControlaNumero()

    fun atualizar(sorteio: SorteioDTO): Long {
        return banco.use().update(Campos.SORTEIO_TABLE.nome, preencheCV(sorteio)," ${Campos.SORTEIO_ID.nome}=${sorteio.idSorteio} ",null).toLong()
    }

    fun salvar(sorteio : SorteioDTO): Long {
        return banco.use().insert(Campos.SORTEIO_TABLE.nome, null, preencheCV(sorteio))
    }

    private fun preencheCV(sorteio: SorteioDTO): ContentValues {
        var cv = ContentValues()
        if(sorteio.idSorteio==null)cv.putNull(Campos.SORTEIO_ID.nome) else cv.put(Campos.SORTEIO_ID.nome,sorteio.idSorteio)
        cv.put(Campos.SORTEIO_APOSTA_ID.nome, sorteio.idAposta)
        cv.put(Campos.SORTEIO_NUMERO_SORTEIO.nome, sorteio.numeroSorteio)
        cv.put(Campos.SORTEIO_DATA_VERIFICACAO.nome, sorteio.dataVerificacaoSorteio.toMyString())
        cv.put(Campos.SORTEIO_QTD_QUADRA.nome,sorteio.quadra)
        cv.put(Campos.SORTEIO_QTD_QUINA.nome,sorteio.quina)
        cv.put(Campos.SORTEIO_QTD_SENA.nome,sorteio.sena)
        cv.put(Campos.SORTEIO_MAIOR_QTD_ACERTOS.nome,sorteio.maiorQuantidadeAcertos)
        cv.put(Campos.SORTEIO_NUMEROS_SORTEADOS.nome,controlaNumeros.numerosToMyString(sorteio.numerosSorteados))
        cv.put(Campos.SORTEIO_NUMEROS_ACERTADOS.nome, controlaNumeros.numerosToMyString(sorteio.numerosAcertados))
        return cv
    }
}
