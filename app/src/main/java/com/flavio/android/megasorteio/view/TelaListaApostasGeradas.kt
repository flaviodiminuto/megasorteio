package com.flavio.android.megasorteio.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.adapter.ListaApostaAdapter
import com.flavio.android.megasorteio.model.Aposta

class TelaListaApostasGeradas : AppCompatActivity() {

    private lateinit var apostas : ArrayList<Aposta>
    private lateinit var recyclerview : RecyclerView
    private lateinit var layout : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_lista_apostas_geradas)

        apostas = gerarApostas()
        listaApostas()
    }

    private fun listaApostas() {
        recyclerview = findViewById(R.id.tela_lista_apostas_geradas_lista)
        layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = layout
        recyclerview.adapter = ListaApostaAdapter(apostas)
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
}
