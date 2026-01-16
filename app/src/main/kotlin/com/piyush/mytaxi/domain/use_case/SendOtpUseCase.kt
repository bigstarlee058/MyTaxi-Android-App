package com.piyush.mytaxi.domain.use_case

import android.app.Activity
import com.google.firebase.auth.PhoneAuthProvider
import com.piyush.mytaxi.domain.repository.AuthRepository

class SendOtpUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(activity: Activity, phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        authRepository.sendOtp(activity, phoneNumber, callbacks)
    }
}