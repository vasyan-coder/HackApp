package com.vasyancoder.data

import android.content.Context
import com.vasyancoder.feature_login.domain.repository.UserRepository
import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase

class RepositoryImpl(
    private val context: Context
) : UserRepository {


    override suspend fun authenticateUser(
        username: String,
        password: String
    ): AuthenticateUserUseCase.AuthenticationResult {
        TODO("Not yet implemented")
    }

    override fun storeUserSession(userId: String) {
        TODO("Not yet implemented")
    }
}