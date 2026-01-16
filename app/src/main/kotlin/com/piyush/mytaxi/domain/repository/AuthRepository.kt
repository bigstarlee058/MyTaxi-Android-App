package com.piyush.mytaxi.domain.repository

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

interface AuthRepository {
    fun sendOtp(activity: Activity, phoneNumber: String, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks)
    suspend fun verifyOtp(credential: PhoneAuthCredential)
}