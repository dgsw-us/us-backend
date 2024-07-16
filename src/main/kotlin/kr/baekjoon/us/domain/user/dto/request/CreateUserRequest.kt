package kr.baekjoon.us.domain.user.dto.request

import org.springframework.format.annotation.DateTimeFormat
import java.util.*

data class CreateUserRequest(
    val username: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val birthDate: Date,
    val userId: String,
    val email: String,
    val password: String
)
