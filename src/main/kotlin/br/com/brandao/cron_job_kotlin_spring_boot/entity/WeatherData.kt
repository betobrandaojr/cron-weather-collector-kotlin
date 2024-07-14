package br.com.brandao.cron_job_kotlin_spring_boot.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "weather_data")
data class WeatherData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "temperature")
    val temperature: Double,

    @Column(name = "windspeed")
    val windSpeed: Double,

    @Column(name = "timestamp")
    val timestamp: LocalDateTime
)