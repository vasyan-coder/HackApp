package com.vasyancoder.data

import android.content.Context
import com.vasyancoder.data.database.HackAppDatabase
import com.vasyancoder.data.database.model.User
import com.vasyancoder.feature_registration.domain.RegistrationRepository
import com.vasyancoder.feature_registration.domain.RegistrationUserUseCase

class RegistrationRepositoryImpl(
    private val context: Context
) : RegistrationRepository {

    private val db = HackAppDatabase.getDatabase(context)
    private val userDao = db.userDao()

    override suspend fun registrationUser(
        login: String,
        password: String,
        email: String
    ): RegistrationUserUseCase.RegistrationResult {
        userDao.insertUser(
            User(
                uid = 0,
                login = login,
                password = password,
                email = email
            )
        )
        return RegistrationUserUseCase.RegistrationResult.Success
    }
}