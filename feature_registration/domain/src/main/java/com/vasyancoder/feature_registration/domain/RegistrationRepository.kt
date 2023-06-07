package com.vasyancoder.feature_registration.domain

interface RegistrationRepository {

    suspend fun registrationUser(
        login: String,
        password: String,
        email: String,
    ): RegistrationUserUseCase.RegistrationResult
}