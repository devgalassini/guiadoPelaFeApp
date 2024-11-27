/*
 * Copyright © 2024 Priscila De Oliveira Galassini
 * Todos os direitos reservados.
 *
 * Este código é protegido por direitos autorais e não pode ser copiado, modificado ou redistribuído sem permissão expressa.
 * Se você tiver permissão para usar este código, ele é fornecido "no estado em que se encontra", sem garantia de qualquer tipo.
 */

package br.com.tecnology.galassini.rotadaf.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import br.com.tecnology.galassini.rotadaf.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Boas-vindas
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)

        // Recupera o nome do usuário da Intent
        val userName = intent.getStringExtra("USER_NAME")
        welcomeTextView.text = "Bem-vindo, $userName!"

        // Número de telefone
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val confirmButton = findViewById<Button>(R.id.confirmButton)

        // Switch para notificações
        val notificationsSwitch = findViewById<SwitchCompat>(R.id.notificationsSwitch)
        val notificationStatusTextView = findViewById<TextView>(R.id.notificationStatusTextView)

        // Validação do número de telefone
        confirmButton.setOnClickListener {
            val phoneNumber = phoneEditText.text.toString().trim()
            Log.d("HomeActivity", "Número inserido: $phoneNumber")
            if (isValidPhoneNumber(phoneNumber)) {
                Toast.makeText(this, "Número confirmado: $phoneNumber", Toast.LENGTH_SHORT).show()

                // Navegar para a tela de CollectionsActivity
                val intent = Intent(this, CollectionsActivity::class.java)
                startActivity(intent)
            } else {
                phoneEditText.error = "Número de telefone inválido! Use o formato (XX) XXXXX-XXXX."
            }
        }

        // Controle de notificações
        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                notificationStatusTextView.text = "Notificações ativadas"
            } else {
                notificationStatusTextView.text = "Notificações desativadas"
            }
        }
    }

    // Função para validar número de telefone no formato (XX) XXXXX-XXXX
    private fun isValidPhoneNumber(phone: String): Boolean {
        // Remove todos os caracteres não numéricos
        val sanitizedPhone = phone.replace(Regex("[^\\d]"), "")
        // Verifica se tem 11 dígitos e começa com um DDD válido
        return sanitizedPhone.length == 11 && sanitizedPhone.matches(Regex("^\\d{2}9\\d{8}\$"))
    }
}
