package com.flavio.android.megasorteio.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
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
import com.flavio.android.megasorteio.view.TelaListaApostaUnitaria
import com.flavio.android.megasorteio.view.TelaVerificarSorteio
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

        preencheCampos(id, aposta, quantidade, valor,position)
        holder.view.card_aposta_visualizar.setOnClickListener {
            
            var controller = Controller(holder.view.context)
            aposta = getApostaVisualizar(aposta, controller)
            var intent = Intent(holder.view.context, TelaListaApostaUnitaria::class.java)
            intent.putExtra("aposta", aposta)
            intent.putExtra("action","aposta_editar")
            intent.putExtra("sequencias_editadas",IntArray(0))
            holder.view.context.startActivity(intent)
        }
        holder.view.card_aposta_deletar_icon.setOnClickListener{
            AlertDialog.Builder(holder.view.context)
                    .setTitle("Remover Aposta")
                    .setMessage("Deseja remover aposta realmente?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("SIM"){dialog, which ->
                        var controller = Controller(holder.view.context)
                        controller.deletarApostaSequencia(aposta)
                        apostas.remove(aposta)
                        notifyItemRemoved(position)
                        Toast.makeText(holder.view.context,"Aposta removida",Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("NÃO"){dialog, which ->  }
                    .show()
        }
        holder.view.card_aposta_btn_verificar_aposta.setOnClickListener{
            
            var intent = Intent(holder.view.context,TelaVerificarSorteio::class.java)
            intent.putExtra("aposta",apostas[position])
            holder.view.context.startActivity(intent)
        }
    }

    private fun getApostaVisualizar(aposta: Aposta, controller: Controller): Aposta {
        var apostaRetorno = aposta
        apostaRetorno = controller.pesquisarApostaComSequencia(apostaRetorno.idAposta)
        var sequencias = mutableListOf<Sequencia>()
        sequencias.addAll(apostaRetorno.sequencias)
        apostaRetorno.sequencias.clear()
        apostaRetorno.adicionarSequenciaList(controller.consultarSequenciasFixas())
        apostaRetorno.adicionarSequenciaList(sequencias)
        apostaRetorno.removeSequenciasFixasDuplicadas(sequencias.size)
        return apostaRetorno
    }

    private fun preencheCampos(id: TextView, aposta: Aposta, quantidade: TextView, valor: TextView,position: Int) {
        id.text = "Aposta: ${aposta.idAposta}"
        quantidade.text = "Quantidade de sequencias: ${aposta.quantidadeSequencias}"
        valor.text = "Valor da aposta:  ${aposta.valor.formataParaMoedaBrasileira()}"
    }

    override fun getItemCount() = apostas.size

    override fun getItemViewType(position: Int) = position
}