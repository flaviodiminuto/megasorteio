package com.flavio.android.megasorteio.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.model.Sequencia
import kotlinx.android.synthetic.main.activity_editar_sequencia.*

class TelaEditarSequencia : AppCompatActivity() {

    lateinit var sequencia: Sequencia

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_sequencia)

        sequencia = intent.extras.get("sequencia") as Sequencia

        var campos = listOf<EditText>(
                editar_sequencia_n1,
                editar_sequencia_n2,
                editar_sequencia_n3,
                editar_sequencia_n4,
                editar_sequencia_n5,
                editar_sequencia_n6,
                editar_sequencia_n7,
                editar_sequencia_n8,
                editar_sequencia_n9,
                editar_sequencia_n10,
                editar_sequencia_n11,
                editar_sequencia_n12,
                editar_sequencia_n13,
                editar_sequencia_n14,
                editar_sequencia_n15 )

        //Eu sei que não é uma solução segura mas para este projeto é viável
        //a convesao de Long para Int neste for (mesmo assim, NÃO RECOMENDO fazê-lo)
        for( i : Int in 0 until  sequencia.numeros.size){
            campos[i].setText(sequencia.numeros[i].toString())
        }

        editar_sequencia_btn_salvar.setOnClickListener{4
            sequencia.numeros = lerCampos(campos)
            var intent = Intent(this, TelaListaApostasTodas::class.java)
            startActivity(intent)
        }

    }
    fun lerCampos(campos : List<EditText>): MutableList<Int> {
        var numeros = mutableListOf<Int>()
        for(campo in campos){
            val numero = digito(campo)
            if(numero>0) numeros.add(numero)
        }
        return numeros
    }
    private fun digito(digito : EditText): Int{
        return if(digito.text.toString() == "") 0 else digito.text.toString().toInt()
    }
}
