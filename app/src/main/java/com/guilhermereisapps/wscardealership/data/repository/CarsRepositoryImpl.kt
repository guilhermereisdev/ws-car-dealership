package com.guilhermereisapps.wscardealership.data.repository

import com.guilhermereisapps.wscardealership.data.model.APIResponse
import com.guilhermereisapps.wscardealership.data.remote.api.ApiService
import com.guilhermereisapps.wscardealership.domain.repository.CarsRepository

class CarsRepositoryImpl(private val apiService: ApiService) : CarsRepository {
    override suspend fun getCars(): APIResponse {
        return apiService.getCars()
    }
}
