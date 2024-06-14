package com.guilhermereisapps.wscardealership.di

import com.guilhermereisapps.wscardealership.data.remote.RetrofitInstance
import com.guilhermereisapps.wscardealership.data.remote.api.ApiService
import com.guilhermereisapps.wscardealership.data.repository.CarsRepositoryImpl
import com.guilhermereisapps.wscardealership.domain.repository.CarsRepository
import com.guilhermereisapps.wscardealership.domain.usecase.GetCarsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApiService() = RetrofitInstance.apiService

    @Provides
    @Singleton
    fun provideCarsRepository(apiService: ApiService): CarsRepository {
        return CarsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetCarsUseCase(carsRepository: CarsRepository): GetCarsUseCase {
        return GetCarsUseCase(carsRepository)
    }
}
