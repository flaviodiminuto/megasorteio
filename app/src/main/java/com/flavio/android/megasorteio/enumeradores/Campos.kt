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
    APOSTA_SEQUENCIA_APOSTA("aposta_sequencia_aposta_id");

    fun get() = valueOf(nome)

}