package kr.baekjoon.us.global.exception

import org.springframework.http.HttpStatus

data class BusinessException (
    val code: HttpStatus,
    val msg: String
) : RuntimeException()