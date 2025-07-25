package uz.example.cicd

object LoginValidator {
    fun isEmailValid(email: String): Boolean = email.contains("@")

    fun isPasswordValid(password: String): Boolean = password.length >= 6
}
