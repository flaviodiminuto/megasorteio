package com.flavio.android.megasorteio.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.card_aposta.view.*

class ListaApostaAdapter(private val apostas : ArrayList<Aposta>) :
        RecyclerView.Adapter<ListaApostaAdapter.ListaApostaViewHolder>(){

    class ListaApostaViewHolder (val view : View) : RecyclerView.ViewHolder (view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaApostaAdapter.ListaApostaViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_aposta,parent,false) as View
        return ListaApostaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaApostaViewHolder, position: Int) {
        var id = holder.view.card_id_aposta
        var quantidade  = holder.view.card_aposta_quantidade_sequencias
        var valor = holder.view.card_aposta_Valor
        var aposta = apostas[position]

        preencheCampos(id, aposta, quantidade, valor)
    }

    private fun preencheCampos(id: TextView, aposta: Aposta, quantidade: TextView, valor: TextView) {
        id.text = "Aposta: ${aposta.idAposta.toString()}"
        quantidade.text = "Quantidade de sequencias: ${aposta.sequencias.size.toString()}"
        valor.text = "Valor da aposta: R$ ${aposta.valor.toString()}"
    }

    override fun getItemCount() = apostas.size


}