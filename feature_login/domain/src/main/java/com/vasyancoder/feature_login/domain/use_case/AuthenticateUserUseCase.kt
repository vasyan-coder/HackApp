package com.vasyancoder.feature_login.domain.use_case

import com.vasyancoder.feature_login.domain.repository.UserRepository

class AuthenticateUserUseCase(private val userRepository: UserRepository) {

    data class UserCredentials(val username: String, val password: String)

    suspend operator fun invoke(userCredentials: UserCredentials): AuthenticationResult {
        return userRepository.authenticateUser(userCredentials.username, userCredentials.password)
    }

    sealed class AuthenticationResult {
        object Success : AuthenticationResult()
        data class Error(val message: String) : AuthenticationResult()
    }
}
