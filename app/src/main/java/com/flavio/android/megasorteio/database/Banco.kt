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
                " ${Campos.SEQUENCIA_DATA_CADASTRO.nome} TEXT ," +
                " ${Campos.SEQUENCIA_DATA_ATUALIZACAO.nome} TEXT, " +
                " ${Campos.SEQUENCIA_N1.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N2.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N3.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N4.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N5.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N6.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N7.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N8.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N9.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N10.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N11.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N12.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N13.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N14.nome} INTEGER , " +
                " ${Campos.SEQUENCIA_N15.nome} INTEGER ) "
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

    fun use(): SQLiteDatabase{
        return this.writableDatabase
    }
}