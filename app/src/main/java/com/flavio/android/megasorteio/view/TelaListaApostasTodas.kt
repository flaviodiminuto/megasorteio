package com.flavio.android.megasorteio.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.adapter.ListaApostasTodasAdapter
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.model.Aposta

class TelaListaApostasTodas : AppCompatActivity() {

    private lateinit var apostas : MutableList<Aposta>
    private lateinit var recyclerview : RecyclerView
    private lateinit var layout : LinearLayoutManager
    private lateinit var ca : Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_lista_apostas_geradas)

        ca = Controller(this)
        apostas = ca.listarApostas()
        listaApostas()
    }

    private fun listaApostas() {
        recyclerview = findViewById(R.id.tela_lista_apostas_geradas_lista)
        layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = layout
        recyclerview.adapter = ListaApostasTodasAdapter(apostas)
    }

    private fun gerarApostas(): ArrayList<Aposta> {
        val apostas = arrayListOf<Aposta>()
        for(i: Int in 0..5) {
            var aposta = Aposta()
            aposta.adicionarSequencia((i+i+2)*3, (i+1)*3)
            aposta.idAposta = i.toLong()
            apostas.add(aposta)
        }
        return apostas
    }

    override fun onBackPressed() = startActivity(Intent(this,Inicio::class.java))
}
