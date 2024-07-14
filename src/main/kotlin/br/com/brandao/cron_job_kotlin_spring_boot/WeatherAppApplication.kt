package br.com.brandao.cron_job_kotlin_spring_boot


import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherAppApplication

fun main(args: Array<String>) {
	val dotenv = Dotenv.configure().directory("./").ignoreIfMalformed().ignoreIfMissing().load()

	dotenv.entries().forEach { entry ->
		System.setProperty(entry.key, entry.value)
	}

	runApplication<WeatherAppApplication>(*args)
}