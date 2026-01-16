package com.piyush.mytaxi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.piyush.mytaxi.ui.MainScreen
import com.piyush.mytaxi.ui.PhoneAuthScreen
import com.piyush.mytaxi.ui.OtpVerificationScreen

sealed class Screen(val route: String) {
    object PhoneAuth : Screen("phone_auth")
    object OtpVerification : Screen("otp_verification/{verificationId}") {
        fun createRoute(verificationId: String) = "otp_verification/$verificationId"
    }
    object Main : Screen("main")
}

@Composable
fun NavGraph(startDestination: String = Screen.PhoneAuth.route) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.PhoneAuth.route) {
            PhoneAuthScreen(navController = navController)
        }
        composable(Screen.OtpVerification.route) {
            val verificationId = it.arguments?.getString("verificationId") ?: ""
            OtpVerificationScreen(navController = navController, verificationId = verificationId)
        }
        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }
    }
}