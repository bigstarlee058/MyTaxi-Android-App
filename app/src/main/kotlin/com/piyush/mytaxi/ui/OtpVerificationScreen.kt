package com.piyush.mytaxi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.piyush.mytaxi.navigation.Screen
import com.piyush.mytaxi.viewmodel.AuthUiState
import com.piyush.mytaxi.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(
    navController: NavController,
    verificationId: String,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var otp by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            label = { Text("OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = { viewModel.verifyOtp(verificationId, otp) }) {
            Text("Verify OTP")
        }

        when (uiState) {
            is AuthUiState.Loading -> CircularProgressIndicator()
            is AuthUiState.OtpVerified -> {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.PhoneAuth.route) {
                        inclusive = true
                    }
                }
            }
            is AuthUiState.Error -> {
                val message = (uiState as AuthUiState.Error).message
                Text(text = message)
            }
            else -> {}
        }
    }
}