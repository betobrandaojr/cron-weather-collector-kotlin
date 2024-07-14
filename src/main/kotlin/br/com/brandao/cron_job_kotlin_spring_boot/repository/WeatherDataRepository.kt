package br.com.brandao.cron_job_kotlin_spring_boot.repository

import br.com.brandao.cron_job_kotlin_spring_boot.entity.WeatherData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WeatherDataRepository : JpaRepository<WeatherData, Long>
