package com.guilhermereisapps.wscardealership.utils.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilhermereisapps.wscardealership.presentation.view.CarsForSaleScreen
import com.guilhermereisapps.wscardealership.presentation.viewmodel.CarsForSaleViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.CarsForSaleScreen.name
    ) {
        composable(ScreensNavigation.CarsForSaleScreen.name) {
            val viewModel = hiltViewModel<CarsForSaleViewModel>()
            CarsForSaleScreen(navController, viewModel)
        }
        composable(ScreensNavigation.SplashScreen.name) {
//            SplashScreen(navController)
        }
        composable(ScreensNavigation.LoginScreen.name) {
//            LoginScreen(navController)
        }
        composable(ScreensNavigation.CreateAccountScreen.name) {
//            CreateAccountScreen(navController)
        }
        composable(ScreensNavigation.UserOptions.name) {
//            UserOptionsScreen(navController)
        }
    }
}
