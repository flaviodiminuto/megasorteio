package com.flavio.android.megasorteio.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import com.flavio.android.megasorteio.R
import kotlinx.android.synthetic.main.activity_inicio.*

class Inicio : AppCompatActivity() {

    lateinit var vibe : Vibrator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        btnInicioGerarJogo.setOnClickListener{
            
            startActivity(Intent(this, GerarAposta::class.java))
        }
        btnInicioVerificarApostas.setOnClickListener{
            startActivity(Intent(this,TelaListaApostasTodas::class.java))
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        System.exit(0)

    }


}
