package com.flavio.android.megasorteio.view

import android.content.Context
import android.graphics.Color
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
import com.flavio.android.megasorteio.model.Sequencia
import kotlinx.android.synthetic.main.activity_tela_verificar_sorteio.*

@Suppress("DEPRECATION")
class TelaVerificarSorteio : AppCompatActivity() {
    lateinit var aposta : Aposta
    lateinit var campos : ArrayList<EditText>
    lateinit var numeros : MutableList<Int>
    lateinit var vibe : Vibrator
    lateinit var maiorSequenciaAcertada : MutableList<Int>
    //7 possibilidades de quantidades de acertos (0,1,2,3,quadra,quina ou sena) cada sequencia incrementa em uma quantidade de acerto
    private var acertos =  limpaAcertos()
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_tela_verificar_sorteio)
        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        aposta = intent.extras.get("aposta") as Aposta
        aposta = Controller(this).pesquisarApostaComSequencia(aposta.idAposta)
        this.campos = arrayListOf(
        verificar_sorteio_n1,
        verificar_sorteio_n2,
        verificar_sorteio_n3,
        verificar_sorteio_n4,
        verificar_sorteio_n5,
        verificar_sorteio_n6 )
        configuraCampos()
        verificar_sorteio_btn_verificar.setOnClickListener{
            var repetidosNosCampos = false
            verificar_sorteio_n1.requestFocus()
            this.numeros = lerNumeros()
            for(i  in 0 until  numeros.size){
                for(j in i until numeros.size)
                    if(j!=i && numeros[i]==numeros[j]) repetidosNosCampos = true
                if(repetidosNosCampos) break
            }
            when {
                verificar_sorteio_edt_numero_sorteio.text.toString()=="" -> Toast.makeText(this, "Informe o número do Sorteio", Toast.LENGTH_LONG).show()
                repetidosNosCampos -> Toast.makeText(this, "Números repetidos", Toast.LENGTH_LONG).show()
                this.numeros.size==6 -> {
                    verificarAcertos(this.numeros)
                    verificar_sorteio_quadra.text = "Quadra:\t${acertos[4]}"
                    verificar_sorteio_quina.text = "Quina:\t${acertos[5]}"
                    verificar_sorteio_sena.text = "Sena:\t${acertos[6]}"
                    /*Toast.makeText(this, "0: ${acertos[0]}\n" +
                            "1: ${acertos[1]}\n" +
                            "2: ${acertos[2]}\n" +
                            "3: ${acertos[3]}\n" +
                            "4: ${acertos[4]}\n" +
                            "5: ${acertos[5]}\n" +
                            "6: ${acertos[6]}", Toast.LENGTH_LONG).show()*/
                }
                else -> Toast.makeText(this, "Preencha os seis números do sorteio", Toast.LENGTH_LONG).show()
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
    }  //Fim do onCreate
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
        verificar_sorteio_n1.setOnFocusChangeListener { _, _ ->
            apagaCampos()
        }
    }

    /**
     * Por hora a consulta do banco não será utilizada diretamente para verificar os acertos e assim
     * não serão verificados todas as apostas do banco neste momento pois não solucionei como solicitar
     * somente as sequencias onde ocorrem 4,5 ou 6 acertos.
     */
    private fun verificarSorteio() {
        var apostas = Controller(this).verificarSorteio(this.numeros)
        println(apostas)
    }

    private fun verificarAcertos(numeros : MutableList<Int>){
        apagaCampos()
        this.acertos  = limpaAcertos()
        var maiorQuantidade: Int = 0
        for(sequencia in aposta.sequencias){
            var camposContidos = aposta.numerosContidos(sequencia, Sequencia(numeros))
            if (camposContidos.size>0) {
                if(camposContidos.size>maiorQuantidade){
                    maiorQuantidade=camposContidos.size
                    maiorSequenciaAcertada = camposContidos
                }
            acertos[camposContidos.size]++
            }
        }
        acendeCamposSorteados(maiorSequenciaAcertada)
    }

    private fun acendeCamposSorteados(camposContidos: MutableList<Int>) {
        for (j: Int in 0 until camposContidos.size) {
            for (i: Int in 0 until this.campos.size)
                if (campos[i].text.toString().toInt() == camposContidos[j]) {
                    acendeCampo(campos[i],true)
                }
        }
    }

    private fun acendeCampo(campo: EditText, acende : Boolean) {
        if(acende) {
            verificar_sorteio_texto_acertos.setTextColor(Color.GREEN)
            campo.setTextColor(Color.GREEN)
            campo.background = resources.getDrawable(R.drawable.bordas_arredondadas_escuras)
        }else{
            verificar_sorteio_texto_acertos.setTextColor(resources.getColor(R.color.texto_cor))
            campo.setTextColor(resources.getColor(R.color.colorPrimaryDark))
            campo.background = resources.getDrawable(R.drawable.bordas_arredondadas)
        }
    }
    private fun apagaCampos(){
        for(campo in campos){
            acendeCampo(campo,false)
        }
    }

    fun limpaAcertos() = mutableListOf<Int>(0,0,0,0,0,0,0)
}

