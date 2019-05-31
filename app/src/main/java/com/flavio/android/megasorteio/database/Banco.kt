package com.flavio.android.megasorteio.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public const val db_name = "banco"
private const val version = 1

class Banco(context: Context) : SQLiteOpenHelper(context, db_name,null,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        createApostaTable(db)
        createSequenciaTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun createApostaTable(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS aposta " +
                "(id_aposta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "valor REAL)"
        db?.execSQL(sql)
    }
    private fun createSequenciaTable(db: SQLiteDatabase?){
        val sql = "CREATE TABLE IF NOT EXISTS sequencia " +
                "(id_sequencia INTEGER PRIMARY KEY AUTOINCREMENT," +
                " id_aposta INTEGER," +
                " valor REAL  DEFAULT 0," +
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

    fun use(): SQLiteDatabase{
        return this.writableDatabase
    }
}