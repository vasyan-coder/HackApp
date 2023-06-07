package com.vasyancoder.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.vasyancoder.data.database.HackAppDatabase
import com.vasyancoder.feature_login.domain.UserRepository
import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase

class RepositoryImpl(
    private val context: Context
) : UserRepository {

    private val db = HackAppDatabase.getDatabase(context)
    private val userDao = db.userDao()

    override suspend fun authenticateUser(
        login: String,
        password: String
    ): AuthenticateUserUseCase.AuthenticationResult {
        val auth = userDao.authenticateUser(login, password)
        return if (auth) {
            AuthenticateUserUseCase.AuthenticationResult.Success
        } else {
            AuthenticateUserUseCase.AuthenticationResult.Error("Incorrect email or password")
        }
    }

    override fun storeUserSession(userId: String) {
        TODO("Not yet implemented")
    }

    fun getUserRole(login: String): LiveData<String> {
        return userDao.getUserRole(login)
    }
}