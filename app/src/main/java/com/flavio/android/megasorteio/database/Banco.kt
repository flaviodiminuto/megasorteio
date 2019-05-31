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
                " ${Campos.APOSTA_VALOR.nome} REAL)"
        db?.execSQL(sql)
    }
    private fun createSequenciaTable(db: SQLiteDatabase?){
        val sql = "CREATE TABLE IF NOT EXISTS sequencia " +
                "(${Campos.SEQUENCIA_ID.nome} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${Campos.SEQUENCIA_VALOR.nome} REAL  DEFAULT 0," +
                " data_cadastro TEXT ," +
                " data_atualizacao TEXT, " +
                " n1 INTEGER DEFAULT 0, " +
                " n2 INTEGER DEFAULT 0, " +
                " n3 INTEGER DEFAULT 0, " +
                " n4 INTEGER DEFAULT 0, " +
                " n5 INTEGER DEFAULT 0, " +
                " n6 INTEGER DEFAULT 0, " +
                " n7 INTEGER DEFAULT 0, " +
                " n8 INTEGER DEFAULT 0, " +
                " n9 INTEGER DEFAULT 0, " +
                " n10 INTEGER DEFAULT 0, " +
                " n11 INTEGER DEFAULT 0, " +
                " n12 INTEGER DEFAULT 0, " +
                " n13 INTEGER DEFAULT 0, " +
                " n14 INTEGER DEFAULT 0, " +
                " n15 INTEGER DEFAULT 0 ) "
        db?.execSQL(sql)
    }

    private fun createApostaSequenciaTable(db: SQLiteDatabase?){
        val sql = "CREATE TABLE IF NOT EXISTS ${Campos.APOSTA_SEQUENCIA_TABLE.nome} " +
                " ( ${Campos.APOSTA_SEQUENCIA_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ${Campos.APOSTA_SEQUENCIA_APOSTA} INTEGER, " +
                " ${Campos.APOSTA_SEQUENCIA_SEQUENCIA} INTEGER)"
        db?.execSQL(sql)
    }

    fun use(): SQLiteDatabase{
        return this.writableDatabase
    }
}