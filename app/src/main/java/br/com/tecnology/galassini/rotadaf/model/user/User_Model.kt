package br.com.tecnology.galassini.rotadaf.model.user

data class User(val name: String, val email: String, val password: String) {

    // Propriedade computada para segurança da senha
    val passwordStrength: String
        get() = when {
            password.length > 10 -> "Forte"
            password.length > 6 -> "Moderada"
            else -> "Fraca"
        }

    // Método para validação detalhada
    fun isValid(): Boolean {
        return validateName() && validateEmail() && validatePassword()
    }

    private fun validateName(): Boolean {
        if (name.isBlank()) throw IllegalArgumentException("O nome não pode estar vazio.")
        return true
    }

    private fun validateEmail(): Boolean {
        if (!email.contains("@")) throw IllegalArgumentException("Email inválido.")
        return true
    }

    private fun validatePassword(): Boolean {
        if (password.length < 6) throw IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.")
        return true
    }
}