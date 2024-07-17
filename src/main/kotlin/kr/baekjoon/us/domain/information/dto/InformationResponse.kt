package kr.baekjoon.us.domain.information.dto

import kr.baekjoon.us.domain.information.enums.InformationCategory
import kr.baekjoon.us.domain.user.dto.UserResponse
import java.time.LocalDateTime

data class InformationResponse (
    val informationId: Long,
    val title: String,
    val description: String,
    val writer: UserResponse,
    val category: InformationCategory,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)