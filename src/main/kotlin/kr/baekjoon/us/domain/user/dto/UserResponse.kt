package kr.baekjoon.us.domain.user.dto

import java.util.*

data class UserResponse(
    val id: Long,
    val username: String,
    val birthDate: Date,
    val userId: String,
    val email: String,
)
