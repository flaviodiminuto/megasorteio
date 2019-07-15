package com.flavio.android.megasorteio.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.adapter.ListaApostaUnitariaAdapter
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia
import kotlinx.android.synthetic.main.activity_mostrar_aposta_unitaria.*

class TelaListaApostaUnitaria : AppCompatActivity() {
    lateinit var aposta : Aposta
    lateinit var layoutManager: LinearLayoutManager
    var act: String = ""
    private var sequenciasRemovidas = mutableListOf<Sequencia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_aposta_unitaria)
        this.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        try {
            getThisExtras()
            mostra_aposta_unitaria_recycler.setHasFixedSize(true)
            mostra_aposta_unitaria_recycler.layoutManager = this.layoutManager
            mostra_aposta_unitaria_recycler.adapter = ListaApostaUnitariaAdapter(aposta.sequencias, aposta)
            when(act) {
                "aposta_editar" -> mostra_aposta_texto_aposta.text = "Aposta ${aposta.idAposta}"
                "aposta_nova" -> mostra_aposta_texto_aposta.text = "Aposta Nova"
            }
        }catch (e: Exception){
            var intent = Intent(this, TelaListaApostasTodas::class.java)
            startActivity(intent)
        }

        mostrar_aposta_btn_salvar.setOnClickListener{
            Toast.makeText(this,"Salvando aposta!", Toast.LENGTH_LONG).show()
            var intent  = Intent(this, TelaListaApostasTodas::class.java)
            Controller(this).salvarAposta(this.aposta)
            startActivity(intent)
        }

        mostra_aposta_btn_selecionar_sequencia.setOnClickListener {
            var numerostr = mostra_aposta_edt_selecionar.text.toString()
            if(numerostr=="")
                Toast.makeText(this,"Informe o número de uma sequencia",Toast.LENGTH_LONG).show()
            else {
                var position = numerostr.toInt()
                if(position<=this.aposta.sequencias.size){
                    var intent = setIntentTelaEditarSequencia(position)
                    startActivity(intent)
                }else{
                    mostra_aposta_edt_selecionar.setText("")
                    Toast.makeText(this, "Número de sequencia não encontrado",Toast.LENGTH_LONG).show()
                }
            }
        }
        mostra_aposta_edt_selecionar.setOnEditorActionListener{ _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                mostra_aposta_btn_selecionar_sequencia.callOnClick()
                true
            } else {
                false
            }
        }
        btn_adicionar_sequencia.setOnClickListener{
            this.aposta.adicionarSequencia(6)
            var intent = setIntentTelaEditarSequencia(this.aposta.sequencias.size-1)
            startActivity(intent)
        }
    }

    private fun setIntentTelaEditarSequencia(position: Int): Intent {
        var intent = Intent(this, TelaEditarSequencia::class.java)
        intent.putExtra("aposta", this.aposta)
        intent.putExtra("action", this.act)
        intent.putExtra("indice", position)
        return intent
    }

    private fun getThisExtras() {
        this.aposta = intent.extras.get("aposta") as Aposta
        this.act = intent.extras.get("action") as String
    }

    override fun onBackPressed() {
        when(act){
            "aposta_nova"-> startActivity(Intent(this,Inicio::class.java))
            else -> startActivity(Intent(this,TelaListaApostasTodas::class.java))
        }
    }
}

