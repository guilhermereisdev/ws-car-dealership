package com.guilhermereisapps.wscardealership.domain.repository

import com.guilhermereisapps.wscardealership.data.model.APIResponse

interface CarsRepository {
    suspend fun getCars(): APIResponse
}
