package kr.baekjoon.us.domain.food.dto.request

import jakarta.persistence.Id

data class UpdateFoodRequest (
    val id: Long,
    val name: String,
    val description: String
)