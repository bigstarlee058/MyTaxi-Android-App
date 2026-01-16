package com.piyush.mytaxi.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.piyush.mytaxi.domain.use_case.SendOtpUseCase
import com.piyush.mytaxi.domain.use_case.VerifyOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class OtpSent(val verificationId: String) : AuthUiState()
    object OtpVerified : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sendOtpUseCase: SendOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // Auto-retrieval may sign the user in
            // We can handle this case if we want to
        }

        override fun onVerificationFailed(e: com.google.firebase.FirebaseException) {
            _uiState.value = AuthUiState.Error(e.message ?: "Verification failed")
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            _uiState.value = AuthUiState.OtpSent(verificationId)
        }
    }

    fun sendOtp(activity: Activity, phoneNumber: String) {
        _uiState.value = AuthUiState.Loading
        sendOtpUseCase(activity, phoneNumber, callbacks)
    }

    fun verifyOtp(verificationId: String, otp: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val credential = PhoneAuthProvider.getCredential(verificationId, otp)
                verifyOtpUseCase(credential)
                _uiState.value = AuthUiState.OtpVerified
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "OTP verification failed")
            }
        }
    }
}