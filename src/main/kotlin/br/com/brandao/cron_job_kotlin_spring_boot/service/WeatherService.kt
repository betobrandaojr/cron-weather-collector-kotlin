package br.com.brandao.cron_job_kotlin_spring_boot.service

import br.com.brandao.cron_job_kotlin_spring_boot.entity.WeatherData
import br.com.brandao.cron_job_kotlin_spring_boot.repository.WeatherDataRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class WeatherService(
    private val externalApiService: ExternalApiService,
    private val weatherDataRepository: WeatherDataRepository
) {

    private val restTemplate = RestTemplate()
    private val logger = LoggerFactory.getLogger(WeatherService::class.java)
    private val url = "https://jsonplaceholder.typicode.com/posts"

    fun collectAndSaveWeatherData() {
        val weatherDataDto = externalApiService.fetchWeatherData() ?: return

        val weatherData = WeatherData(
            temperature = weatherDataDto.temperature,
            windSpeed = weatherDataDto.windSpeed,
            timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(weatherDataDto.timestamp), ZoneId.systemDefault())
        )

        weatherDataRepository.save(weatherData)
        logger.info("Weather data collected and saved: $weatherData")

        sendWeatherData(weatherData)
    }

    fun sendWeatherData(weatherData: WeatherData) {
        try {
            val response = restTemplate.postForEntity(url, weatherData, String::class.java)
            logger.info("Data sent to external API: $weatherData, Response: ${response.statusCode}")
        } catch (ex: Exception) {
            logger.error("Error sending data to external API: $weatherData, Error: ${ex.message}")
        }
    }
}
