package com.flavio.android.megasorteio.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.extension.InputFilterMinMax
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.ControlaNumero
import com.flavio.android.megasorteio.model.Sequencia
import kotlinx.android.synthetic.main.activity_editar_sequencia.*
import kotlinx.android.synthetic.main.activity_tela_verificar_sorteio.*

class TelaEditarSequencia : AppCompatActivity() {

    lateinit var sequencia : Sequencia
    lateinit var aposta : Aposta
    private var indice : Int = 0
    private var act = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_sequencia)

        this.aposta = intent.extras.get("aposta") as Aposta
        this.indice = intent.extras.get("indice") as Int
        this.act = intent.extras.get("action") as String

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
        sequencia = aposta.sequencias[indice]
        preencheCampos(campos)

        editar_sequencia_btn_salvar.setOnClickListener {

            adicionaSequenciaNaAposta(campos)
            if(aposta.sequencias[indice].tamanho>=6){
                var intent = Intent(this, TelaListaApostaUnitaria::class.java)
                intent . putExtra ("aposta", aposta)
                intent.putExtra("action",act)
                startActivity(intent)
            }else Toast.makeText(this, "Informe ao menos 6 números",Toast.LENGTH_LONG).show()

        }

        editar_sequencia_btn_gerar_automatico.setOnClickListener{
            
            sequencia.numeros = gerarAutomatico()
            sequencia.ordenaNumerosSequencia()
            preencheCampos(campos)
        }
        editar_sequencia_quantidade.setOnEditorActionListener{ _, actionId, _ ->
            
            if(actionId == EditorInfo.IME_ACTION_DONE){
                editar_sequencia_btn_gerar_automatico.callOnClick()
                true
            } else {
                false
            }
        }
        editar_sequencia_n15.setOnEditorActionListener{ _, actionId, _ ->
            
            if(actionId == EditorInfo.IME_ACTION_DONE){
                editar_sequencia_btn_salvar.callOnClick()
                true
            } else {
                false
            }
        }
    }

    private fun adicionaSequenciaNaAposta(campos: List<EditText>) {
        sequencia.numeros = lerCampos(campos)
        sequencia.ordenaNumerosSequencia()
        sequencia.tamanho = sequencia.numeros.size
        sequencia.setValor(sequencia.numeros.size)
        aposta.sequencias[indice] = sequencia
        aposta.setValor()
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

    override fun onBackPressed() {
        
        super.onBackPressed()
    }
}
