package com.textsdev.cleanweatherapp_mvvm.di

import com.textsdev.cleanweatherapp_mvvm.BuildConfig
import com.textsdev.cleanweatherapp_mvvm.data.repository.WeatherRepository
import com.textsdev.cleanweatherapp_mvvm.data.repository.WeatherRepositoryImpl
import com.textsdev.cleanweatherapp_mvvm.network.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(apiService: WeatherApiService): WeatherRepository {
        return WeatherRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}