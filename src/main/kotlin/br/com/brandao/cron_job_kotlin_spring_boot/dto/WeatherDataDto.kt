package br.com.brandao.cron_job_kotlin_spring_boot.dto

data class WeatherDataDto(
    val temperature: Double,
    val windSpeed: Double,
    val timestamp: Long
)