package com.vasyancoder.feature_login.domain.use_case

class ValidateUserInputUseCase {

    data class UserInput(val username: String, val password: String)

    operator fun invoke(userInput: UserInput): ValidationResult {
        return when {
            userInput.username.isBlank() -> ValidationResult.Error("Username can't be empty")
            userInput.password.isBlank() -> ValidationResult.Error("Password can't be empty")
            else -> ValidationResult.Success
        }
    }

    sealed class ValidationResult {
        object Success : ValidationResult()
        data class Error(val message: String) : ValidationResult()
    }
}