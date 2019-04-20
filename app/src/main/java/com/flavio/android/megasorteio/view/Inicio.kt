package com.flavio.android.megasorteio.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.flavio.android.megasorteio.R
import kotlinx.android.synthetic.main.activity_inicio.*

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btnInicioGerarJogo.setOnClickListener{
            val intent = Intent(this, TelaListaApostasGeradas::class.java)
            startActivity(intent)
        }
    }


}
