package com.guilhermereisapps.wscardealership.presentation.view.navigation

enum class ScreensNavigation {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    CarsForSaleScreen,
    UserOptions,
    CarDetailsScreen;

    companion object {
        fun fromRoute(route: String?): ScreensNavigation =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                CreateAccountScreen.name -> CreateAccountScreen
                CarsForSaleScreen.name -> CarsForSaleScreen
                UserOptions.name -> UserOptions
                CarDetailsScreen.name -> CarDetailsScreen
                null -> CarsForSaleScreen
                else -> throw IllegalArgumentException("Rota $route não é reconhecida.")
            }
    }
}
