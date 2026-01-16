package com.piyush.mytaxi.domain.use_case

import com.google.firebase.auth.PhoneAuthCredential
import com.piyush.mytaxi.domain.repository.AuthRepository

class VerifyOtpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(credential: PhoneAuthCredential) {
        authRepository.verifyOtp(credential)
    }
}