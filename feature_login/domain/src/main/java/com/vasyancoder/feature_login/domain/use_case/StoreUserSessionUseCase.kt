package com.vasyancoder.feature_login.domain.use_case

import com.vasyancoder.feature_login.domain.repository.UserRepository

class StoreUserSessionUseCase(private val userRepository: UserRepository) {

    data class UserSession(val userId: String)

    operator fun invoke(userSession: UserSession) {
        userRepository.storeUserSession(userSession.userId)
    }
}
