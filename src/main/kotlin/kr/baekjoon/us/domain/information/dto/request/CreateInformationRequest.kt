package kr.baekjoon.us.domain.information.dto.request

import kr.baekjoon.us.domain.information.enums.InformationCategory

data class CreateInformationRequest (
    val title: String,
    val description: String,
    val category: InformationCategory
)