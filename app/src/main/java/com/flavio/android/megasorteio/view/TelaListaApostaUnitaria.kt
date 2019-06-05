package com.flavio.android.megasorteio.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.adapter.ListaApostaUnitariaAdapter
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.activity_mostrar_aposta_unitaria.*

class TelaListaApostaUnitaria : AppCompatActivity() {
    lateinit var aposta : Aposta
    lateinit var layoutManager: LinearLayoutManager
    var act: String = ""
    lateinit var vibe : Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_aposta_unitaria)
        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        this.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        try {
            this.aposta = intent.extras.get("aposta") as Aposta
            this.act = intent.extras.get("action") as String
            mostra_aposta_unitaria_recycler.setHasFixedSize(true)
            mostra_aposta_unitaria_recycler.layoutManager = this.layoutManager
            mostra_aposta_unitaria_recycler.adapter = ListaApostaUnitariaAdapter(aposta.sequencias, aposta)
            when(act) {
                "aposta_editar" -> {
                    mostra_aposta_texto_aposta.text = "Aposta ${aposta.idAposta}"
                }
                "aposta_editada" -> {
                    mostra_aposta_texto_aposta.text = "Aposta ${aposta.idAposta}"
                    Toast.makeText(this,"Aposta atualizada",Toast.LENGTH_LONG).show()
                }
                "aposta_nova" -> mostra_aposta_texto_aposta.text = "Aposta Nova"
            }
        }catch (e: Exception){
            var intent = Intent(this, TelaListaApostasTodas::class.java)
            startActivity(intent)
        }

        mostrar_aposta_btn_salvar.setOnClickListener{
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            Toast.makeText(this,"Salvando aposta!", Toast.LENGTH_LONG).show()
            var intent  = Intent(this, TelaListaApostasTodas::class.java)
            when(act){
                "aposta_nova"-> {
                        Controller(this).salvarAposta(this.aposta)
                        startActivity(intent)
                    }
                else -> startActivity(Intent(this, TelaListaApostasTodas::class.java))
                }
            }
        mostra_aposta_btn_selecionar_sequencia.setOnClickListener {
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            var numerostr =mostra_aposta_edt_selecionar.text.toString()
                    if(numerostr=="")
                Toast.makeText(this,"Informe o número de uma sequencia",Toast.LENGTH_LONG).show()
            else
                try {
                    var intent = Intent(this, TelaEditarSequencia::class.java)
                    var position = numerostr.toInt()
                    var sequenciaSelecionada = aposta.sequencias[position] //Para gerar erro caso o indice informado nao exista na aposta
                            intent.putExtra("aposta", aposta)
                    when {
                        "aposta_editada"==act || "aposta_editar"==act  -> intent.putExtra("action", "aposta_editar")
                        else -> intent.putExtra("action", "aposta_nova")
                    }
                    intent.putExtra("indice", position)
                    startActivity(intent)
                }catch (e : Exception){
                    mostra_aposta_edt_selecionar.setText("")
                    Toast.makeText(this, "Número de sequencia não encontrado",Toast.LENGTH_LONG).show()
                }
        }
    }
    override fun onBackPressed() {
        vibe.vibrate(VibrationEffect.createOneShot(10,150))
        startActivity(Intent(this,TelaListaApostasTodas::class.java))
    }
}

