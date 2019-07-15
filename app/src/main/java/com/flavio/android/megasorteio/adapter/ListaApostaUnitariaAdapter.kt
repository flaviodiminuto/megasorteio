package com.flavio.android.megasorteio.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.extension.formataParaMoedaBrasileira
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia
import com.flavio.android.megasorteio.view.TelaEditarSequencia
import com.flavio.android.megasorteio.view.TelaListaApostasTodas
import kotlinx.android.synthetic.main.card_sequencia.view.*

class ListaApostaUnitariaAdapter (private val sequencias: MutableList<Sequencia>, private val aposta : Aposta ) :
        RecyclerView.Adapter<ListaApostaUnitariaAdapter.ListaApostaUnitariaViewHolder>(){

    class ListaApostaUnitariaViewHolder ( val view: View ) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaApostaUnitariaAdapter.ListaApostaUnitariaViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_sequencia, parent,false) as View
        return ListaApostaUnitariaViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListaApostaUnitariaViewHolder, position: Int) {
        //Preenchendo os campos do card_sequencia
        when(sequencias[position].idSequencia){
            0L ->  holder.view.card_sequencia_texto.text = "Sequencia $position"
            else -> holder.view.card_sequencia_texto.text = "Sequencia $position"
        }
        holder.view.card_sequencia_valor.text = "Valor: ${sequencias[position].valor.formataParaMoedaBrasileira()}"

        //Preenchendo os campos numericos
        var camposNumericos = mutableListOf<TextView>(
                holder.view.n1,
                holder.view.n2,
                holder.view.n3,
                holder.view.n4,
                holder.view.n5,
                holder.view.n6,
                holder.view.n7,
                holder.view.n8,
                holder.view.n9,
                holder.view.n10,
                holder.view.n11,
                holder.view.n12,
                holder.view.n13,
                holder.view.n14,
                holder.view.n15)

        preencheCamposNumericos(camposNumericos,sequencias[position].numeros)

        //Acao ao clicar em cima do card de uma sequencia
        holder.view.card_sequencia_editar.setOnClickListener{
            
            var intent = Intent(holder.view.context, TelaEditarSequencia::class.java)
            intent.putExtra("aposta", aposta)
            when{
                aposta.idAposta > 0L -> intent.putExtra("action", "aposta_editar")
                else -> intent.putExtra("action", "aposta_nova")
            }
            intent.putExtra("indice",position)
            holder.view.context.startActivity(intent)
        }
        holder.view.card_sequencia_deletar_icon.setOnClickListener{
            var controller = Controller(holder.view.context)
            AlertDialog.Builder(holder.view.context)
                    .setTitle("Remover Sequencia")
                    .setMessage("Deseja remover aposta realmente?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("SIM"){dialog, which ->
                        controller.deletarSequencia(sequencias[position])
                        sequencias.removeAt(position)
                        aposta.quantidadeSequencias=sequencias.size.toLong()
                        aposta.setValor()
                        notifyItemRemoved(position)
                        if(sequencias.size==0) {
                            Toast.makeText(holder.view.context,"Aposta removida",Toast.LENGTH_LONG).show()
                            controller.deletarAposta(aposta)
                            holder.view.context.startActivity(Intent(holder.view.context,TelaListaApostasTodas::class.java))

                        }else{
                            controller.atualizarAposta(aposta)
                            Toast.makeText(holder.view.context,"Sequencia removida",Toast.LENGTH_LONG).show()
                        }
                    }
                    .setNegativeButton("NÃO"){dialog, which ->  }
                    .show()
        }
    }

    private fun preencheCamposNumericos(campos: MutableList<TextView>,numeros : MutableList<Int>) {

        for(i in 0 until campos.size){
            if(i<numeros.size){
                campos[i].text = numeros[i].toString()
                campos[i].visibility = View.VISIBLE
            }else if(numeros.size > 10){
                campos[i].visibility = View.INVISIBLE
            }
        }
    }
    override fun getItemCount() = sequencias.size

    override fun getItemViewType(position: Int) = position
}