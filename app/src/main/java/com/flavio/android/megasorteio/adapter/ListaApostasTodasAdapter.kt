package com.flavio.android.megasorteio.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.card_aposta.view.*

class ListaApostasTodasAdapter(private val apostas : MutableList<Aposta>) :
        RecyclerView.Adapter<ListaApostasTodasAdapter.ListaApostaViewHolder>(){

    class ListaApostaViewHolder (val view : View) : RecyclerView.ViewHolder (view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaApostasTodasAdapter.ListaApostaViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_aposta,parent,false) as View
        return ListaApostaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaApostaViewHolder, position: Int) {
        var id = holder.view.card_id_aposta
        var quantidade  = holder.view.card_aposta_quantidade_sequencias
        var valor = holder.view.card_aposta_Valor
        var aposta = apostas[position]

        holder.view.setOnClickListener {
            Toast.makeText(holder.view.context, "teste ${apostas[position]}", Toast.LENGTH_LONG).show()
        }
        preencheCampos(id, aposta, quantidade, valor)
    }

    private fun preencheCampos(id: TextView, aposta: Aposta, quantidade: TextView, valor: TextView) {
        id.text = "Aposta: ${aposta.idAposta}"
        quantidade.text = "Quantidade de sequencias: ${aposta.quantidadeSequencias}"
        valor.text = "Valor da aposta: R$ ${aposta.valor}"
    }

    override fun getItemCount() = apostas.size

    override fun getItemViewType(position: Int) = position
}