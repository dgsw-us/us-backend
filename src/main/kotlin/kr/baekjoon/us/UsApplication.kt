package kr.baekjoon.us

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UsApplication

fun main(args: Array<String>) {
    runApplication<UsApplication>(*args)
}
