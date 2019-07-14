package com.flavio.android.megasorteio.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
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
    lateinit var sequenciasEditadas :IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_aposta_unitaria)
        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
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
            when(act){
                "aposta_nova" -> Controller(this).salvarAposta(this.aposta)
                "aposta_editar" -> atualizarApostaSequencias()
            }
            startActivity(intent)
        }

        mostra_aposta_btn_selecionar_sequencia.setOnClickListener {
            var numerostr = mostra_aposta_edt_selecionar.text.toString()
            if(numerostr=="")
                Toast.makeText(this,"Informe o número de uma sequencia",Toast.LENGTH_LONG).show()
            else
                try {
                    var position = numerostr.toInt()* //TODO Continuar daqui (14/07/2019 00:24)
                    //TODO - fazer com que o numero seja o da sequencia (id) e nao da posicao - var sequenciaSelecionada = aposta.sequencias[position] //Para gerar erro caso o indice informado nao exista na aposta
                    var intent = setTelaEditarSequenciaIntent(position)
                    startActivity(intent)
                }catch (e : Exception){
                    mostra_aposta_edt_selecionar.setText("")
                    Toast.makeText(this, "Número de sequencia não encontrado",Toast.LENGTH_LONG).show()
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
            var intent = Intent(this, TelaEditarSequencia::class.java)
            intent.putExtra("action",act)
            intent.putExtra("indice",this.aposta.sequencias.size)
            this.aposta.adicionarSequencia(6)
            intent.putExtra("aposta",this.aposta)
            startActivity(intent)
        }
    }

    private fun atualizarApostaSequencias() {
        val controller = Controller(this)
        controller.atualizarAposta(this.aposta)
        for(i : Int in sequenciasEditadas){
            controller.atualizarSequencia(aposta.sequencias[i])
        }
    }

    private fun setTelaEditarSequenciaIntent(position: Int): Intent {
        var intent = Intent(this, TelaEditarSequencia::class.java)
        intent.putExtra("aposta", this.aposta)
        intent.putExtra("action", this.act)
        intent.putExtra("indice", position)
        intent.putExtra("sequencias_editadas", this.sequenciasEditadas.plus(position))
        return intent
    }

    private fun getThisExtras() {
        this.aposta = intent.extras.get("aposta") as Aposta
        this.act = intent.extras.get("action") as String
        this.sequenciasEditadas = intent.extras.get("sequencias_editadas")as IntArray
    }

    override fun onBackPressed() {
        when(act){
            "aposta_nova"-> startActivity(Intent(this,Inicio::class.java))
            else -> startActivity(Intent(this,TelaListaApostasTodas::class.java))
        }
    }
}

