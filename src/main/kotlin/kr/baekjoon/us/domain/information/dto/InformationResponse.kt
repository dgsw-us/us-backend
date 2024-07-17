package kr.baekjoon.us.domain.information.dto

import kr.baekjoon.us.domain.user.dto.UserResponse

data class InformationResponse (
    val informationId: Long,
    val title: String,
    val description: String,
    val writer: UserResponse
)