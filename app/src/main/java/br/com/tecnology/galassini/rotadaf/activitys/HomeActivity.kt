package br.com.tecnology.galassini.rotadaf.activitys


import android.os.Bundle
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
            if (isValidPhoneNumber(phoneNumber)) {
                Toast.makeText(this, "Número confirmado: $phoneNumber", Toast.LENGTH_SHORT).show()
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
        val regex = Regex("^\\(\\d{2}\\) \\d{5}-\\d{4}\$")
        return phone.matches(regex)
    }
}