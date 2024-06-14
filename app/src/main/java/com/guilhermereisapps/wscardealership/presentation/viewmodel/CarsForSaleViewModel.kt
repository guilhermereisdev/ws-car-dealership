package com.guilhermereisapps.wscardealership.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guilhermereisapps.wscardealership.data.model.Car
import com.guilhermereisapps.wscardealership.domain.usecase.GetCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsForSaleViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars

    fun fetchCars() {
        viewModelScope.launch {
            val response = getCarsUseCase.execute()
            _cars.value = response.cars
        }
    }
}
