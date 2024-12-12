package org.example.langeureka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer //Eureka Server
class LangEurekaApplication

fun main(args: Array<String>) {
    runApplication<LangEurekaApplication>(*args)
}
