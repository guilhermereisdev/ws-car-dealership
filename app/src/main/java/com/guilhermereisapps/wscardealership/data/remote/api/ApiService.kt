package com.guilhermereisapps.wscardealership.data.remote.api

import com.guilhermereisapps.wscardealership.data.model.APIResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiService {
    @GET("cars.json")
    suspend fun getCars(): APIResponse
}
