package com.flavio.android.megasorteio.enumeradores

enum class Campos(internal val nome: String) {
    //Tabela aposta
    APOSTA_TABLE("aposta"),
    APOSTA_ID ("id_aposta"),
    APOSTA_VALOR ("valor"),

    //Tabela sequencia
    SEQUENCIA_TABLE("sequencia"),
    SEQUENCIA_ID("id_sequencia"),
    SEQUENCIA_VALOR("valor"),
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
    APOSTA_SEQUENCIA_ID("id_aposta_sequencia"),
    APOSTA_SEQUENCIA_SEQUENCIA("id_sequencia"),
    APOSTA_SEQUENCIA_APOSTA("id_aposta");

    fun get() = valueOf(nome)

}