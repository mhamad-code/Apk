package com.devicex.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devicex.app.screens.DeviceDetailScreen
import com.devicex.app.screens.HomeScreen

// كل مسارات التطبيق مجمّعة هنا بمكان واحد، عشان ما نكرر النصوص بكل مكان
object Routes {
    const val HOME = "home"
    const val DEVICE_DETAIL = "device_detail"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }
        composable(Routes.DEVICE_DETAIL) {
            DeviceDetailScreen(navController = navController)
        }
    }
}
