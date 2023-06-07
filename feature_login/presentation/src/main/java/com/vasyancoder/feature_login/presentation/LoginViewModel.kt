package com.vasyancoder.feature_login.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vasyancoder.data.RepositoryImpl
import com.vasyancoder.feature_login.domain.use_case.AuthenticateUserUseCase
import com.vasyancoder.feature_login.domain.use_case.ValidateUserInputUseCase
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val validateUserInput = ValidateUserInputUseCase()
    private val authenticateUser = AuthenticateUserUseCase(repository)

    lateinit var userRole: LiveData<String>

    private val _authenticationResult =
        MutableLiveData<AuthenticateUserUseCase.AuthenticationResult>()
    val authenticationResult: LiveData<AuthenticateUserUseCase.AuthenticationResult> =
        _authenticationResult

    fun authenticateUser(login: String, password: String) {
        viewModelScope.launch {
            val authentication =
                authenticateUser(AuthenticateUserUseCase.UserCredentials(login, password))
            userRole = repository.getUserRole(login)
            _authenticationResult.postValue(authentication)
        }
    }
}