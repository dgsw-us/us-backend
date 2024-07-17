package kr.baekjoon.us.domain.information.dto

import kr.baekjoon.us.domain.user.dto.UserResponse
import java.time.LocalDateTime

data class InformationResponse (
    val informationId: Long,
    val title: String,
    val description: String,
    val writer: UserResponse,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)