package com.vasyancoder.feature_registration.domain

class RegistrationUserUseCase(private val repository: RegistrationRepository) {

    data class UserCredentials(
        val login: String,
        val password: String,
        val email: String
        )


    suspend operator fun invoke(userCredentials: UserCredentials): RegistrationResult {
        return repository.registrationUser(
            userCredentials.login,
            userCredentials.password,
            userCredentials.email
        )
    }

    sealed class RegistrationResult {
        object Success : RegistrationResult()
        data class Error(val message: String) : RegistrationResult()
    }
}