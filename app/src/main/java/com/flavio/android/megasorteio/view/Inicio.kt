package com.flavio.android.megasorteio.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.activity_inicio.*

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        btnInicioGerarJogo.setOnClickListener{
            
            startActivity(Intent(this, GerarAposta::class.java))
        }
        btnInicioVerificarApostas.setOnClickListener{
            startActivity(Intent(this,TelaListaApostasTodas::class.java))
        }

        btnInicioGerarJogoManualmente.setOnClickListener{
            var intent = Intent(this,TelaListaApostaUnitaria::class.java)
            var aposta = Aposta()
            aposta.adicionarSequenciaList(Controller(this).consultarSequenciasFixas())
            intent.putExtra("aposta",aposta)
            intent.putExtra("action","aposta_nova")

            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        System.exit(0)

    }


}
