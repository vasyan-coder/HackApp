package com.vasyancoder.feature_login.domain.repository

import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase

interface UserRepository {

    /**
     * Verifies account information
     */
    suspend fun authenticateUser(
        login: String,
        password: String
    ): AuthenticateUserUseCase.AuthenticationResult

    /**
     * Stores a login in the SharedPref
     * that says that the authorization was successful
     */
    fun storeUserSession(userId: String)
}
