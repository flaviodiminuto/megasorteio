package com.flavio.android.megasorteio.view

import android.content.Intent
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_aposta_unitaria)
        this.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        try {
            this.aposta = intent.extras.get("aposta") as Aposta
            this.act = intent.extras.get("action") as String
            mostra_aposta_unitaria_recycler.setHasFixedSize(true)
            mostra_aposta_unitaria_recycler.layoutManager = this.layoutManager
            mostra_aposta_unitaria_recycler.adapter = ListaApostaUnitariaAdapter(aposta.sequencias, aposta)
            when(act){
                "aposta_editar" -> mostra_aposta_texto_aposta.text = "Aposta ${aposta.idAposta}"
                "aposta_nova" -> mostra_aposta_texto_aposta.text = "Aposta Nova"
            }
        }catch (e: Exception){
            var intent = Intent(this, TelaListaApostasTodas::class.java)
            startActivity(intent)
        }

        when(act) {
            "exibir" -> {
                mostra_aposta_texto_salvar.visibility = View.GONE
                mostrar_aposta_btn_salvar.visibility = View.GONE
            }
        }
        mostrar_aposta_btn_salvar.setOnClickListener{
            if(salvarAposta()>0){
                var intent = Intent(this,TelaListaApostasTodas::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Não foi possível salvarAposta Aposta ", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun salvarAposta() = Controller(this).salvarAposta(this.aposta)

    override fun onBackPressed() = startActivity(Intent(this,Inicio::class.java))
}

