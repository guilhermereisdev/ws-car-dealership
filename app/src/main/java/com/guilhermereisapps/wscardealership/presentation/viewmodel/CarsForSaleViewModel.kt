package com.guilhermereisapps.wscardealership.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermereisapps.wscardealership.data.model.Car
import com.guilhermereisapps.wscardealership.data.model.Compra
import com.guilhermereisapps.wscardealership.domain.repository.CompraRepository
import com.guilhermereisapps.wscardealership.domain.usecase.GetCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsForSaleViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase,
    private val compraRepository: CompraRepository,
) : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars

    private val _selectedCar = MutableStateFlow<Car?>(null)
    val selectedCar: StateFlow<Car?> get() = _selectedCar

    fun fetchCars() {
        viewModelScope.launch {
            val response = getCarsUseCase.execute()
            _cars.value = response.cars
        }
    }

    fun selectCar(car: Car) {
        _selectedCar.value = car
    }

    fun insertCompra(compra: Compra) {
        viewModelScope.launch {
            compraRepository.insertCompra(compra)
        }
    }
}
