package com.rukavina.gymbuddy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GymBuddyApp() {
    val navController = rememberNavController()
    GymBuddyAppContainer(navController)
}