package com.flavio.android.megasorteio.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.model.Aposta
import kotlinx.android.synthetic.main.activity_tela_verificar_sorteio.*

class TelaVerificarSorteio : AppCompatActivity() {
    lateinit var aposta : Aposta
    lateinit var campos : ArrayList<EditText>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_verificar_sorteio)
        campos = arrayListOf(
                verificar_sorteio_n1,
                verificar_sorteio_n2,
                verificar_sorteio_n3,
                verificar_sorteio_n4,
                verificar_sorteio_n5,
                verificar_sorteio_n6 )
    }
}
