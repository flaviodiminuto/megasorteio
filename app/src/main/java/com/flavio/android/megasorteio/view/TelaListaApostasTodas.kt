package com.flavio.android.megasorteio.view

import android.content.Context
import android.content.Intent
import android.database.CursorIndexOutOfBoundsException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputFilter
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.adapter.ListaApostasTodasAdapter
import com.flavio.android.megasorteio.controller.Controller
import com.flavio.android.megasorteio.extension.InputFilterMinMax
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.activity_tela_lista_apostas_geradas.*
import kotlinx.android.synthetic.main.activity_tela_verificar_sorteio.*

class TelaListaApostasTodas : AppCompatActivity() {

    private lateinit var apostas : MutableList<Aposta>
    private lateinit var recyclerview : RecyclerView
    private lateinit var layout : LinearLayoutManager
    private lateinit var ca : Controller
    lateinit var vibe : Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_lista_apostas_geradas)
        vibe = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        ca = Controller(this)
        apostas = ca.listarApostas()
        listaApostas()
        mostra_aposta_btn_selecionar_sequencia.setOnClickListener{
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            var numero: Long
            if(mostra_aposta_edt_selecionar.text.toString()!=""){
                numero = mostra_aposta_edt_selecionar.text.toString().toLong()
                try{
                    var aposta : Aposta = ca.pesquisarApostaComSequencia(numero)
                    if( aposta.idAposta>0){
                        var intent = Intent(this,TelaListaApostaUnitaria::class.java)
                        intent.putExtra("aposta", aposta)
                        intent.putExtra("action","aposta_editar")
                        startActivity(intent)
                    }
                }catch (e : CursorIndexOutOfBoundsException){
                    mostra_aposta_edt_selecionar.setText("")
                    Toast.makeText(this, "Número de aposta não encontrado",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Informe o numero da aposta",Toast.LENGTH_LONG).show()
            }
        }
        mostra_aposta_edt_selecionar.setOnEditorActionListener{ _, actionId, _ ->
            vibe.vibrate(VibrationEffect.createOneShot(10,150))
            if(actionId == EditorInfo.IME_ACTION_DONE){
                mostra_aposta_btn_selecionar_sequencia.callOnClick()
                true
            } else {
                false
            }
        }
}

private fun listaApostas() {
    recyclerview = findViewById(R.id.tela_lista_apostas_geradas_lista)
    layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    recyclerview.setHasFixedSize(true)
    recyclerview.layoutManager = layout
    recyclerview.adapter = ListaApostasTodasAdapter(apostas)
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

    override fun onBackPressed(){
        vibe.vibrate(VibrationEffect.createOneShot(10,150))
        startActivity(Intent(this,Inicio::class.java))
    }
}
