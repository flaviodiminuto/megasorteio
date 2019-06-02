package com.flavio.android.megasorteio.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.adapter.ListaApostasTodasAdapter
import kotlinx.android.synthetic.main.activity_inicio.*

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btnInicioGerarJogo.setOnClickListener{
            startActivity(Intent(this, GerarAposta::class.java))
        }
        btnInicioVerificarSorteio.setOnClickListener{
            startActivity(Intent(this,TelaListaApostasTodas::class.java))
        }
    }


}
