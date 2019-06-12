package com.flavio.android.megasorteio.enumeradores

enum class Campos(internal val nome: String) {
    //Tabela aposta
    APOSTA_TABLE("aposta"),
    APOSTA_ID ("id_aposta"),
    APOSTA_VALOR ("aposta_valor"),
    APOSTA_QUANTIDADE_SEQUENCIAS("quantidade_sequencia"),

    //Tabela sequencia
    SEQUENCIA_TABLE("sequencia"),
    SEQUENCIA_ID("id_sequencia"),
    SEQUENCIA_VALOR("sequencia_valor"),
    SEQUENCIA_TAMANHO("sequencia_tamanho"),
    SEQUENCIA_DATA_CADASTRO("data_cadastro"),
    SEQUENCIA_DATA_ATUALIZACAO("data_atualizacao"),
    SEQUENCIA_NUMEROS("numeros"),

    //Tabela aposta_sequenica
    APOSTA_SEQUENCIA_TABLE("aposta_sequencia"),
    APOSTA_SEQUENCIA_ID("aposta_sequencia_id"),
    APOSTA_SEQUENCIA_SEQUENCIA("aposta_sequencia_sequencia_id"),
    APOSTA_SEQUENCIA_APOSTA("aposta_sequencia_aposta_id"),

    //Tabela sorteio aposta
    SORTEIO_TABLE("sorteio_aposta"),
    SORTEIO_ID("sorteioa_id"),
    SORTEIO_APOSTA_ID("sorteio_aposta_id"),
    SORTEIO_NUMERO_SORTEIO("sorteio_numero_sorteio"),
    SORTEIO_DATA_VERIFICACAO("sorteio_data_verificacao"),
    SORTEIO_QTD_QUADRA("sorteio_quantidade_quadra"),
    SORTEIO_QTD_QUINA("sorteio_quantidade_quina"),
    SORTEIO_QTD_SENA("sorteio_quantidade_sena"),
    SORTEIO_MAIOR_QTD_ACERTOS("sorteio_maior_quantidade_acertos"),
    SORTEIO_NUMEROS_SORTEADOS("sorteio_numeros_sorteados"),
    SORTEIO_NUMEROS_ACERTADOS("sorteio_numeros_acertados");

    fun get() = valueOf(nome)

}