package com.cerulik.scratchcard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cerulik.scratchcard.scratch.ScratchScreen
import com.cerulik.scratchcard.ui.activation.ActivationScreen
import com.cerulik.scratchcard.ui.home.HomeScreen

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object ScratchScreen : Screen("scratch_screen")
    data object ActivationScreen : Screen("activation_screen")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                onGoToScratchClick = {
                    navController.navigate(Screen.ScratchScreen.route)
                },
                onGoToActivationClick = {
                    navController.navigate(Screen.ActivationScreen.route)
                }
            )
        }
        composable(route = Screen.ScratchScreen.route) {
            ScratchScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screen.ActivationScreen.route) {
            ActivationScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}