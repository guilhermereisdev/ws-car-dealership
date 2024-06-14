package com.guilhermereisapps.wscardealership.domain.usecase

import com.guilhermereisapps.wscardealership.data.model.APIResponse
import com.guilhermereisapps.wscardealership.domain.repository.CarsRepository

class GetCarsUseCase(private val carsRepository: CarsRepository) {
    suspend fun execute(): APIResponse {
        return carsRepository.getCars()
    }
}
