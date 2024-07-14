package br.com.brandao.cron_job_kotlin_spring_boot.config

import br.com.brandao.cron_job_kotlin_spring_boot.service.WeatherService
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class SchedulerConfig(
    private val weatherService: WeatherService
) {

    @Scheduled(cron = "1 * * * * *")
    fun scheduleWeatherDataCollection() {
        weatherService.collectAndSaveWeatherData()
    }

}