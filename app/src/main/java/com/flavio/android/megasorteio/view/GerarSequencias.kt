package com.flavio.android.megasorteio.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.activity_gerar_sequencias.*
import java.io.Serializable

class GerarSequencias : AppCompatActivity() {
    private var aposta = Aposta()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerar_sequencias)

        btnGerarAutomaticoGerarSequencia.setOnClickListener{
            gerarSequencias()
            if(!aposta.sequencias.isEmpty()){
                var intent = Intent(this,MostrarApostaUnitaria::class.java)
                intent.putExtra("aposta",this.aposta)
                startActivity(intent)
            }
        }

        edtGerar15.setOnEditorActionListener{ _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                btnGerarAutomaticoGerarSequencia.callOnClick()
                true
            } else {
                false
            }
        }

        btnAutomaticoReturn.setOnClickListener{
            onBackPressed()
        }
    }

    private fun gerarSequencias(): Aposta{
        var tamanho = 6
        aposta.sequencias.clear()
        for(quantidade : Int in lerQuantidades()){
            this.aposta.adicionarSequencia(quantidade,tamanho)
            tamanho++
        }
        return this.aposta
    }

    override fun onBackPressed() {
        val intent = Intent(this,Inicio::class.java)
        startActivity(intent)
    }
    private fun lerQuantidades() : ArrayList<Int> {
        val quantidades = ArrayList<Int>()
        quantidades.add( digito(edtGerar6))
        quantidades.add( digito(edtGerar7))
        quantidades.add( digito(edtGerar8))
        quantidades.add( digito(edtGerar9))
        quantidades.add( digito(edtGerar10))
        quantidades.add( digito(edtGerar11))
        quantidades.add( digito(edtGerar12))
        quantidades.add( digito(edtGerar13))
        quantidades.add( digito(edtGerar14))
        quantidades.add( digito(edtGerar15))

        return quantidades
    }

    private fun digito(digito : EditText): Int{
        return if(digito.text.toString() == "") 0 else digito.text.toString().toInt()
    }

}
