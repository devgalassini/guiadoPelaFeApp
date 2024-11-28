/*
 * Copyright © 2024 Priscila De Oliveira Galassini
 * Todos os direitos reservados.
 *
 * Este código é protegido por direitos autorais e não pode ser copiado, modificado ou redistribuído sem permissão expressa.
 * Se você tiver permissão para usar este código, ele é fornecido "no estado em que se encontra", sem garantia de qualquer tipo.
 */


package br.com.tecnology.galassini.rotadaf.activitys

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnology.galassini.rotadaf.R

class WidgetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widgets)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Exibir texto ao clicar no botão
        button.setOnClickListener {
            val input = editText.text.toString()
            if (input.isNotEmpty()) {
                textView.text = "Você digitou: $input"
                Toast.makeText(this, "Texto exibido com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                editText.error = "Digite algo antes de continuar!"
            }
        }

        // Alterar a imagem ao pressionar o botão
        button.setOnLongClickListener {
            imageView.setImageResource(R.drawable.igreja)
            Toast.makeText(this, "Imagem alterada!", Toast.LENGTH_SHORT).show()
            true
        }
    }
}