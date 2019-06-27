package com.flavio.android.megasorteio.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.flavio.android.megasorteio.enumeradores.Campos

const val db_name = "banco"
private const val version = 1


class Banco(context: Context) : SQLiteOpenHelper(context, db_name,null,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        createApostaTable(db)
        createSequenciaTable(db)
        createApostaSequenciaTable(db)
        createSorteioAposta(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createApostaTable(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS ${Campos.APOSTA_TABLE.nome} " +
                "(${Campos.APOSTA_ID.nome} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${Campos.APOSTA_VALOR.nome} REAL," +
                " ${Campos.APOSTA_QUANTIDADE_SEQUENCIAS.nome} INTEGER)"
        db?.execSQL(sql)
    }
    private fun createSequenciaTable(db: SQLiteDatabase?){
        val sql = "CREATE TABLE IF NOT EXISTS sequencia " +
                "(${Campos.SEQUENCIA_ID.nome} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${Campos.SEQUENCIA_VALOR.nome} REAL  DEFAULT 0," +
                " ${Campos.SEQUENCIA_TAMANHO.nome} INTEGER ," +
                " ${Campos.SEQUENCIA_DATA_CADASTRO.nome} TEXT ," +
                " ${Campos.SEQUENCIA_DATA_ATUALIZACAO.nome} TEXT, " +
                " ${Campos.SEQUENCIA_NUMEROS.nome} TEXT ," +
                " ${Campos.SEQUENCIA_FIXA.nome} INTEGER ) "
        db?.execSQL(sql)
    }

    private fun createApostaSequenciaTable(db: SQLiteDatabase?){
        val sql = "CREATE TABLE IF NOT EXISTS ${Campos.APOSTA_SEQUENCIA_TABLE.nome} " +
                " ( ${Campos.APOSTA_SEQUENCIA_ID.nome} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ${Campos.APOSTA_SEQUENCIA_APOSTA.nome} INTEGER, " +
                " ${Campos.APOSTA_SEQUENCIA_SEQUENCIA.nome} INTEGER, " +
                " FOREIGN KEY(${Campos.APOSTA_SEQUENCIA_APOSTA.nome}) REFERENCES ${Campos.APOSTA_TABLE.nome}(${Campos.APOSTA_ID.nome}) ON DELETE CASCADE ON UPDATE CASCADE," +
                " FOREIGN KEY(${Campos.APOSTA_SEQUENCIA_SEQUENCIA.nome}) REFERENCES ${Campos.SEQUENCIA_TABLE.nome}(${Campos.SEQUENCIA_ID.nome}) ON DELETE CASCADE ON UPDATE CASCADE )"
        db?.execSQL(sql)
    }

    private fun createSorteioAposta(db: SQLiteDatabase?){
        val sql = " CREATE TABLE IF NOT EXISTS ${Campos.SORTEIO_TABLE.nome} " +
                "( ${Campos.SORTEIO_ID.nome} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ${Campos.SORTEIO_APOSTA_ID.nome} INTEGER, " +
                " ${Campos.SORTEIO_NUMERO_SORTEIO.nome} INTEGER, " +
                " ${Campos.SORTEIO_DATA_VERIFICACAO.nome} TEXT, " +
                " ${Campos.SORTEIO_QTD_QUADRA.nome} INTEGER, " +
                " ${Campos.SORTEIO_QTD_QUINA.nome} INTEGER, " +
                " ${Campos.SORTEIO_QTD_SENA.nome} INTEGER, " +
                " ${Campos.SORTEIO_SEQUENCIA_COM_MAIS_ACERTOS.nome} INTEGER, "
                " ${Campos.SORTEIO_MAIOR_QTD_ACERTOS.nome} INTEGER," +
                " ${Campos.SORTEIO_NUMEROS_ACERTADOS.nome} TEXT, " +
                " ${Campos.SORTEIO_NUMEROS_SORTEADOS.nome} TEXT, " +
                " FOREIGN KEY(${Campos.SORTEIO_APOSTA_ID.nome}) REFERENCES ${Campos.APOSTA_TABLE.nome}(${Campos.APOSTA_ID.nome}) ON DELETE CASCADE ON UPDATE CASCADE +\n" +
                " FOREIGN KEY(${Campos.SORTEIO_SEQUENCIA_COM_MAIS_ACERTOS.nome}) REFERENCES ${Campos.SEQUENCIA_TABLE.nome}(${Campos.SEQUENCIA_ID.nome}) ON DELETE CASCADE ON UPDATE CASCADE ) "
        db?.execSQL(sql)
    }

    fun use(): SQLiteDatabase{
        return this.writableDatabase
    }
}