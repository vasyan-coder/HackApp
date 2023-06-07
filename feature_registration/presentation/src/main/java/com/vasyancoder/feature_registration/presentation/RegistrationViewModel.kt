package com.vasyancoder.feature_registration.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vasyancoder.data.RegistrationRepositoryImpl
import com.vasyancoder.feature_registration.domain.RegistrationUserUseCase
import com.vasyancoder.feature_registration.domain.ValidateUserInputUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RegistrationRepositoryImpl(application)
    private val validateUserInput = ValidateUserInputUseCase()
    private val registerUser = RegistrationUserUseCase(repository)

    private val _registrationResult =
        MutableLiveData<RegistrationUserUseCase.RegistrationResult>()
    val registrationResult: LiveData<RegistrationUserUseCase.RegistrationResult> =
        _registrationResult

    private val _validation = MutableLiveData<String>()
    val validation = _validation

    fun registerUser(login: String, password: String, confirmPassword: String, email: String) {

        val validate = validateUserInput(
            ValidateUserInputUseCase.UserInput(
                username = login,
                password = password,
                confirmPassword = confirmPassword,
                email = email
            )
        )
        if (validate == ValidateUserInputUseCase.ValidationResult.Success) {
            viewModelScope.launch {
                val register =
                    registerUser(RegistrationUserUseCase.UserCredentials(login, password, email))
                _registrationResult.postValue(register)
            }
        } else if (validate is ValidateUserInputUseCase.ValidationResult.Error ){
            Log.d(TAG, validate.message)
            _validation.value = validate.message
        }
    }

    companion object {

        private const val TAG = "RegistrationViewModel"
    }
}