package kr.baekjoon.us.global.common

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

data class BaseResponse<T> (
    val code: HttpStatus,
    val message: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T? = null
)
