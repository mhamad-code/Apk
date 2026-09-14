package com.devicex.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devicex.app.screens.CpuDetailScreen
import com.devicex.app.screens.DeviceDetailScreen
import com.devicex.app.screens.HomeScreen
import com.devicex.app.screens.RamDetailScreen
import com.devicex.app.screens.SettingsScreen

object Routes {
    const val HOME = "home"
    const val DEVICE_DETAIL = "device_detail"
    const val CPU_DETAIL = "cpu_detail"
    const val RAM_DETAIL = "ram_detail"
    const val SETTINGS = "settings"
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
        composable(Routes.CPU_DETAIL) {
            CpuDetailScreen(navController = navController)
        }
        composable(Routes.RAM_DETAIL) {
            RamDetailScreen(navController = navController)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(navController = navController)
        }
    }
}
