/*
 * Copyright © 2024 Priscila De Oliveira Galassini
 * Todos os direitos reservados.
 *
 * Este código é protegido por direitos autorais e não pode ser copiado, modificado ou redistribuído sem permissão expressa.
 * Se você tiver permissão para usar este código, ele é fornecido "no estado em que se encontra", sem garantia de qualquer tipo.
 */

package br.com.tecnology.galassini.rotadaf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import br.com.tecnology.galassini.rotadaf.activitys.HomeActivity
import br.com.tecnology.galassini.rotadaf.functions.showToast
import br.com.tecnology.galassini.rotadaf.functions.validateFields
import br.com.tecnology.galassini.rotadaf.model.user.User


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val nameEditText = findViewById<AppCompatEditText>(R.id.nameEditText)
        val emailEditText = findViewById<AppCompatEditText>(R.id.emailEditText)
        val passwordEditText = findViewById<AppCompatEditText>(R.id.passwordEditText)
        val cadastroButton = findViewById<Button>(R.id.cadastroButton)

        cadastroButton.setOnClickListener {
            try {
                val user = getUserFromInput(nameEditText, emailEditText, passwordEditText)
                if (user.isValid()) {
                    handleSuccessfulRegistration(user)
                } else {
                    showError("Por favor, preencha todos os campos corretamente!")
                }
            } catch (e: IllegalArgumentException) {
                showError(e.message ?: "Erro inesperado!")
            }
        }


    }


    private fun getUserFromInput(
        nameEditText: AppCompatEditText,
        emailEditText: AppCompatEditText,
        passwordEditText: AppCompatEditText
    ): User {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (validateFields(nameEditText, emailEditText, passwordEditText) { it.isNotEmpty() }) {
            showMessage("Todos os campos foram preenchidos!")
        }


        return User(name, email, password)
    }

    // Função para lidar com o sucesso
    private fun handleSuccessfulRegistration(user: User) {
        // Redirecionar para a nova tela
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("USER_NAME", user.name)
        startActivity(intent)
        finish() // Fecha a tela de cadastro
    }


    // Função para exibir mensagens de erro
    private fun showError(message: String) {
        //  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        showToast(this,message)
    }

    // Função para exibir mensagens de sucesso
    private fun showMessage(message: String) {
        // Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        showToast(this,message)
    }





}