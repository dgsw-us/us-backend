package kr.baekjoon.us.domain.information.dto.request

data class UpdateInformationRequest (
    val informationId: Long,
    val title: String,
    val description: String
)