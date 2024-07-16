package kr.baekjoon.us.domain.user.dto.request

import java.util.*

data class ModifyUserRequest (
    val username: String,
    val birthDate: Date,
    val userId: String,
    val email: String,
)