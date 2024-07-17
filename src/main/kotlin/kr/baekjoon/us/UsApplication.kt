package kr.baekjoon.us

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class UsApplication

fun main(args: Array<String>) {
    runApplication<UsApplication>(*args)
}
