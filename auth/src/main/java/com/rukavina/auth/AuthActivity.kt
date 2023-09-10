package com.rukavina.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rukavina.auth.ui.theme.GymBuddyTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymBuddyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(
                        onLoginClick = {},
                        navController
                    ) }
                    composable("register") { RegistrationScreen(
                        onSignUpClick = {},
                        navController
                    ) }
                }
            }
        }
    }
}