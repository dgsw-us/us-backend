package kr.baekjoon.us.global.handler

import kr.baekjoon.us.global.common.BaseResponse
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    fun handleException(e: BusinessException): ResponseEntity<BaseResponse<Void>> {
        val response: BaseResponse<Void> = BaseResponse(e.code, e.msg)

        return ResponseEntity.status(e.code).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<BaseResponse<Void>> {
        e.printStackTrace()

        val response: BaseResponse<Void> = BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다")

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}