package com.flavio.android.megasorteio.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.InputFilter
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.extension.InputFilterMinMax
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.activity_gerar_sequencias.*
import kotlinx.android.synthetic.main.activity_tela_verificar_sorteio.*

class TelaVerificarSorteio : AppCompatActivity() {
    lateinit var aposta : Aposta
    lateinit var campos : ArrayList<EditText>
    lateinit var numeros : MutableList<Int>
    lateinit var vibe : Vibrator
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tela_verificar_sorteio)
        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        this.campos = arrayListOf(
        verificar_sorteio_n1,
        verificar_sorteio_n2,
        verificar_sorteio_n3,
        verificar_sorteio_n4,
        verificar_sorteio_n5,
        verificar_sorteio_n6 )
        configuraCampos()
        verificar_sorteio_btn_verificar.setOnClickListener{
            this.numeros = lerNumeros()
            if(this.numeros.size==6){
                verificarSorteio()
            }else{
                Toast.makeText(this, "Preencha os seis números do sorteio", Toast.LENGTH_LONG).show()
            }
        }
        verificar_sorteio_n6.setOnEditorActionListener{ _, actionId, _ ->
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            if(actionId == EditorInfo.IME_ACTION_DONE){
                verificar_sorteio_btn_verificar.callOnClick()
                true
            } else {
                false
            }
        }
    }
    fun lerNumeros(): MutableList<Int> {
        var numeros = mutableListOf<Int>()
        for(campo in this.campos){
            if(campo.text.isNotEmpty())
             numeros.add(campo.text.toString().toInt())
        }
        return numeros
    }
    fun configuraCampos(){
        for(campo in this.campos){
            campo.filters = arrayOf<InputFilter>(InputFilterMinMax(1,60))
        }
    }
    private fun verificarSorteio() {
        var apostas = Controller(this).verificarSorteio(this.numeros)
        println(apostas)
    }
}
