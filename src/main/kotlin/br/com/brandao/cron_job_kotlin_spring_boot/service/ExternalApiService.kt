package br.com.brandao.cron_job_kotlin_spring_boot.service

import br.com.brandao.cron_job_kotlin_spring_boot.dto.WeatherDataDto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ExternalApiService {

    private val restTemplate = RestTemplate()

    fun fetchWeatherData(): WeatherDataDto? {
        val response = restTemplate.getForEntity(
            "https://api.open-meteo.com/v1/forecast?latitude=35.6895&longitude=139.6917&current_weather=true",
            Map::class.java
        )
        val currentWeather = response.body?.get("current_weather") as? Map<String, Any> ?: return null

        return WeatherDataDto(
            temperature = currentWeather["temperature"] as Double,
            windSpeed = currentWeather["windspeed"] as Double,
            timestamp = System.currentTimeMillis()
        )
    }
}