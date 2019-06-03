package com.flavio.android.megasorteio.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.widget.EditText
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.extension.InputFilterMinMax
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.ControlaNumero
import com.flavio.android.megasorteio.model.Sequencia
import kotlinx.android.synthetic.main.activity_editar_sequencia.*

class TelaEditarSequencia : AppCompatActivity() {

    lateinit var sequencia : Sequencia
    lateinit var aposta : Aposta
    var indice : Int = 0
    var act = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_sequencia)

        aposta = intent.extras.get("aposta") as Aposta
        indice = intent.extras.get("indice") as Int
        act = intent.extras.get("action") as String
        sequencia = aposta.sequencias[indice]

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
        for(campo in campos){
            campo.filters = arrayOf<InputFilter>(InputFilterMinMax(1,60))
        }
        editar_sequencia_quantidade.filters = arrayOf<InputFilter>(InputFilterMinMax(1,15))

        //Eu sei que não é uma solução segura mas para este projeto é viável
        //a convesao de Long para Int neste for (mesmo assim, NÃO RECOMENDO fazê-lo)
        preencheCampos(campos)

        editar_sequencia_btn_salvar.setOnClickListener {
            sequencia.numeros = lerCampos(campos)
            sequencia.ordenaNumerosSequencia()
            when (act) {
                "aposta_nova" -> {

                    var intent = Intent(this, TelaListaApostaUnitaria::class.java)
                    intent.putExtra("action", "aposta_nova")
                    intent . putExtra ("aposta", aposta)
                    startActivity(intent)
                }
                "aposta_editar" ->{
                    //ATUALIZAR APOSTA NO BANCO
                    Controller(this).atualizarSequencia(sequencia)

                    var intent = Intent(this, TelaListaApostaUnitaria::class.java)
                    intent.putExtra("action", "aposta_nova")
                    intent . putExtra ("aposta", aposta)
                    startActivity(intent)
                }
            }
        }

        editar_sequencia_btn_gerar_automatico.setOnClickListener{
            sequencia.numeros = gerarAutomatico()
            preencheCampos(campos)
        }
    }

    private fun preencheCampos(campos: List<EditText>) {
        for (i: Int in 0 until campos.size) {
            campos[i].setText("")
        }
        for (i: Int in 0 until sequencia.numeros.size) {
            campos[i].setText(sequencia.numeros[i].toString())
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

    private fun gerarAutomatico(): MutableList<Int> {
        var quantidade : Int = if(editar_sequencia_quantidade.text.toString()=="")
            6
        else
            editar_sequencia_quantidade.text.toString().toInt()
        if(quantidade< 6 ) quantidade = 6
        return ControlaNumero().preencheNumerosSequencia(quantidade)
    }


}
