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
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            startActivity(Intent(this, GerarAposta::class.java))
        }
        btnInicioVerificarApostas.setOnClickListener{
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            startActivity(Intent(this,TelaListaApostasTodas::class.java))
        }
        btnInicioVerificarSorteio.setOnClickListener{
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            startActivity(Intent(this,TelaVerificarSorteio::class.java))
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        System.exit(0)

    }


}
