package com.vasyancoder.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vasyancoder.data.database.model.User
import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query(
        "SELECT EXISTS(SELECT * FROM User WHERE login=:login AND password=:password)"
    )
    suspend fun authenticateUser(
        login: String,
        password: String
    ): Boolean

    @Query("SELECT role FROM User WHERE login=:login")
    fun getUserRole(login: String): LiveData<String>
}