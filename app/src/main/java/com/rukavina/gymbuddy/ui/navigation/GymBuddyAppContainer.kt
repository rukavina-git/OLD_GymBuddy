package com.rukavina.gymbuddy.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rukavina.gymbuddy.ui.home.HomeScreen
import androidx.compose.runtime.getValue

@Composable
fun GymBuddyAppContainer(navController: NavController) {
    Scaffold(
        topBar = {
            // Toolbar goes here
            TopAppBar(
                title = { Text("My App") },
                // Add menu items or actions as needed
            )
        },
        bottomBar = {
            // Bottom navigation goes here
            BottomNavigation {
                // Define bottom navigation items
            }
        }
    ) { contentPadding ->
        // Apply content padding to your main content
        Column(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            // NavHost for navigating between screens
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            when (navBackStackEntry?.destination?.route) {
                "home" -> HomeScreen(navController)
                // Add more screens as needed
            }
        }
    }
}
