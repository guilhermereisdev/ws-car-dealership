package com.guilhermereisapps.wscardealership.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guilhermereisapps.wscardealership.presentation.view.CarDetailsScreen
import com.guilhermereisapps.wscardealership.presentation.view.CarsForSaleScreen
import com.guilhermereisapps.wscardealership.presentation.viewmodel.CarsForSaleViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    carsForSaleViewModel: CarsForSaleViewModel = hiltViewModel<CarsForSaleViewModel>(),
) {
    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.CarsForSaleScreen.name
    ) {
        composable(ScreensNavigation.CarsForSaleScreen.name) {
            CarsForSaleScreen(
                viewModel = carsForSaleViewModel,
                onCarClick = { car ->
                    carsForSaleViewModel.selectCar(car)
                    navController.navigate(ScreensNavigation.CarDetailsScreen.name)
                }
            )
        }
        composable(ScreensNavigation.CarDetailsScreen.name) {
            val selectedCar = carsForSaleViewModel.selectedCar.collectAsState().value
            selectedCar?.let { car -> CarDetailsScreen(car) }
        }
        composable(ScreensNavigation.SplashScreen.name) {
//            SplashScreen()
        }
    }
}
