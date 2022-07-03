package com.yodgorbekkomilov.weatherapps.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.yodgorbekkomilov.weatherapps.data.mappers.toWeatherInfo
import com.yodgorbekkomilov.weatherapps.data.remote.WeatherApi
import com.yodgorbekkomilov.weatherapps.domain.repository.WeatherRepository
import com.yodgorbekkomilov.weatherapps.domain.util.Resource
import com.yodgorbekkomilov.weatherapps.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}