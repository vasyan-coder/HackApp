package com.vasyancoder.data

import android.content.Context
import com.vasyancoder.data.database.HackAppDatabase
import com.vasyancoder.feature_login.domain.repository.UserRepository
import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val context: Context
) : UserRepository {

    private val db = HackAppDatabase.getDatabase(context)
    private val userDao = db.userDao()

    override suspend fun authenticateUser(
        login: String,
        password: String
    ): AuthenticateUserUseCase.AuthenticationResult {
        val auth = db.userDao().authenticateUser(login, password)
        return if (auth) {
            AuthenticateUserUseCase.AuthenticationResult.Success
        } else {
            AuthenticateUserUseCase.AuthenticationResult.Error("Incorrect email or password")
        }
    }

    override fun storeUserSession(userId: String) {
        TODO("Not yet implemented")
    }
}