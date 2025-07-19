package com.example.gyrocard_sample.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gyrocard_sample.ui.component.BottomNavigationBar
import com.example.gyrocard_sample.ui.screen.HomeScreen
import com.example.gyrocard_sample.ui.screen.PersonalScreen
import com.example.gyrocard_sample.ui.screen.TeamScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("personal") { PersonalScreen() }
            composable("team") { TeamScreen() }
        }
    }
}
