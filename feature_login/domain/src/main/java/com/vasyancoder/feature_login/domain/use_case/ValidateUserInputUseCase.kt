package com.vasyancoder.feature_login.domain.use_case

import android.util.Patterns

class ValidateUserInputUseCase {

    data class UserInput(val username: String, val password: String)

    operator fun invoke(userInput: UserInput): ValidationResult {
        return when {
            userInput.username.isBlank() -> ValidationResult.Error("Username can't be empty")
            userInput.password.isBlank() -> ValidationResult.Error("Password can't be empty")
            !isValidEmail(userInput.username) -> ValidationResult.Error("Invalid email format")
            userInput.password.length < 8 -> ValidationResult.Error("Password must be at least 8 characters long")
            else -> ValidationResult.Success
        }
    }

    private fun isValidEmail(email: String): Boolean {
        if (email.isBlank()) {
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }
        return true
    }

    sealed class ValidationResult {
        object Success : ValidationResult()
        data class Error(val message: String) : ValidationResult()
    }
}