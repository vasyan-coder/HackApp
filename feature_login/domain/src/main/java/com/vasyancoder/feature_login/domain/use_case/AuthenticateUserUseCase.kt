package com.vasyancoder.feature_login.domain.use_case

import com.vasyancoder.feature_login.domain.UserRepository


class AuthenticateUserUseCase(private val userRepository: UserRepository) {

    data class UserCredentials(val login: String, val password: String)

    suspend operator fun invoke(userCredentials: UserCredentials): AuthenticationResult {
        return userRepository.authenticateUser(userCredentials.login, userCredentials.password)
    }

    sealed class AuthenticationResult {
        object Success : AuthenticationResult()
        data class Error(val message: String) : AuthenticationResult()
    }
}
