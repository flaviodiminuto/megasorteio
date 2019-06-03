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
    SEQUENCIA_N1("n1"),
    SEQUENCIA_N2("n2"),
    SEQUENCIA_N3("n3"),
    SEQUENCIA_N4("n4"),
    SEQUENCIA_N5("n5"),
    SEQUENCIA_N6("n6"),
    SEQUENCIA_N7("n7"),
    SEQUENCIA_N8("n8"),
    SEQUENCIA_N9("n9"),
    SEQUENCIA_N10("n10"),
    SEQUENCIA_N11("n11"),
    SEQUENCIA_N12("n12"),
    SEQUENCIA_N13("n13"),
    SEQUENCIA_N14("n14"),
    SEQUENCIA_N15("n15"),

    //Tabela aposta_sequenica
    APOSTA_SEQUENCIA_TABLE("aposta_sequencia"),
    APOSTA_SEQUENCIA_ID("aposta_sequencia_id"),
    APOSTA_SEQUENCIA_SEQUENCIA("aposta_sequencia_sequencia_id"),
    APOSTA_SEQUENCIA_APOSTA("aposta_sequencia_aposta_id");

    fun get() = valueOf(nome)

}