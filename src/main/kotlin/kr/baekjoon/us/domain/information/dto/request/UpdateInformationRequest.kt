package kr.baekjoon.us.domain.information.dto.request

import kr.baekjoon.us.domain.information.enums.InformationCategory

data class UpdateInformationRequest (
    val informationId: Long,
    val title: String,
    val description: String,
    val category: InformationCategory
)