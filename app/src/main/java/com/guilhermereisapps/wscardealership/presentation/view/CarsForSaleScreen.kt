package com.guilhermereisapps.wscardealership.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.guilhermereisapps.wscardealership.R
import com.guilhermereisapps.wscardealership.data.model.Car
import com.guilhermereisapps.wscardealership.presentation.viewmodel.CarsForSaleViewModel
import com.guilhermereisapps.wscardealership.presentation.components.AppBar
import com.guilhermereisapps.wscardealership.presentation.components.CarCard

@Composable
fun CarsForSaleScreen(
    navController: NavHostController,
    viewModel: CarsForSaleViewModel,
    onCarClick: (Car) -> Unit,
) {
    val cars = viewModel.cars.collectAsState()
    viewModel.fetchCars()

    Scaffold(
        topBar = { AppBar(title = "Lista de Carros") }
    ) { topBarPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(topBarPadding)
                .consumeWindowInsets(topBarPadding)
        ) {
            CarsList(cars = cars.value, onCarClick = { onCarClick(it) })
        }
    }
}

@Composable
fun CarsList(
    modifier: Modifier = Modifier,
    cars: List<Car>,
    onCarClick: (Car) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(cars) { car ->
            when (car.modeloId) {
                12 -> car.image = R.drawable.onixplus
                14 -> car.image = R.drawable.jetta
                79 -> car.image = R.drawable.hilux
            }
            CarCard(car = car, onClick = { onCarClick(car) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarsForSaleScreenPreview() {
    CarsList(
        modifier = Modifier.background(
            color = androidx.compose.ui.graphics.Color.White
        ),
        cars = listOf(
            Car(
                ano = 2015,
                combustivel = "FLEX",
                cor = "BEGE",
                id = 1,
                modeloId = 12,
                modeloNome = "ONIX PLUS",
                numPortas = 4,
                timestampCadastro = 1696539488,
                valor = 50.000
            ),
            Car(
                ano = 2014,
                combustivel = "FLEX",
                cor = "AZUL",
                id = 2,
                modeloId = 14,
                modeloNome = "JETTA",
                numPortas = 4,
                timestampCadastro = 1696531234,
                valor = 49.000
            ),
            Car(
                ano = 1993,
                combustivel = "DIESEL",
                cor = "AZUL",
                id = 3,
                modeloId = 79,
                modeloNome = "HILLUX SW4",
                numPortas = 4,
                timestampCadastro = 16965354321,
                valor = 47.500
            ),
            Car(
                ano = 2017,
                combustivel = "Diesel",
                cor = "Vermelho",
                id = 4,
                modeloId = 104,
                modeloNome = "Modelo W",
                numPortas = 4,
                timestampCadastro = 1625100600,
                valor = 55.000
            ),
            Car(
                ano = 2021,
                combustivel = "Elétrico",
                cor = "Azul",
                id = 5,
                modeloId = 105,
                modeloNome = "Modelo V",
                numPortas = 4,
                timestampCadastro = 1625101600,
                valor = 60.000
            )
        ),
        onCarClick = {},
    )
}
