package io.github.krisalord.validation


import io.github.krisalord.config.ValidationException

object AuthValidation {
    fun validateEmail(email: String) {
        if (!email.contains("@") || email.length < 5) {
            throw ValidationException("Invalid email format")
        }
    }

    fun validatePassword(password: String) {
        if (password.length < 8) {
            throw ValidationException("Password must be at least 8 characters")
        }

        if (!password.any {it.isDigit()}) {
            throw ValidationException("Password must contain at least one number")
        }

        if (!password.any {it.isLetter()}) {
            throw ValidationException("Password must contain at least one letter")
        }
    }
}