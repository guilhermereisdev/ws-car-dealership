package com.guilhermereisapps.wscardealership.data.remote

import com.guilhermereisapps.wscardealership.data.remote.api.ApiService
import com.guilhermereisapps.wscardealership.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
