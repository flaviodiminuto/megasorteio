package com.flavio.android.megasorteio.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.model.Aposta

class MostrarApostaUnitaria : AppCompatActivity() {
    lateinit var aposta : Aposta
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_aposta_unitaria)

        this.aposta = intent.extras.get("aposta") as Aposta
        Toast.makeText(this, aposta.mostraTodasSequencias(),Toast.LENGTH_LONG).show()
    }
}
