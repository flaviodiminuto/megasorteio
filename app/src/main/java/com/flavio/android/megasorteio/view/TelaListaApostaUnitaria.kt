package com.flavio.android.megasorteio.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.adapter.ListaApostaUnitariaAdapter
import com.flavio.android.megasorteio.controller.ControlaAposta
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.activity_mostrar_aposta_unitaria.*

class TelaListaApostaUnitaria : AppCompatActivity() {
    lateinit var aposta : Aposta
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_aposta_unitaria)
        this.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        this.aposta = intent.extras.get("aposta") as Aposta

        Toast.makeText(this, "Aposta\n$aposta",Toast.LENGTH_LONG).show()
        try {
            mostra_aposta_unitaria_recycler.setHasFixedSize(true)
            mostra_aposta_unitaria_recycler.layoutManager = this.layoutManager
            mostra_aposta_unitaria_recycler.adapter = ListaApostaUnitariaAdapter(aposta.sequencias)
        }catch (e: Exception){
            var intent = Intent(this, TelaListaApostasTodas::class.java)
            startActivity(intent)
        }

        mostrar_aposta_btn_salvar.setOnClickListener{
            if(salvarAposta()>0){
                var intent = Intent(this,TelaListaApostasTodas::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Não foi possível salvar Aposta ", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun salvarAposta() = ControlaAposta(this).salvar(this.aposta)
}

