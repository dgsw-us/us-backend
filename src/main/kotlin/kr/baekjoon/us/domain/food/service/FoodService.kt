package kr.baekjoon.us.domain.food.service

import kr.baekjoon.us.domain.food.dto.FoodResponse
import kr.baekjoon.us.domain.food.dto.request.CreateFoodRequest
import kr.baekjoon.us.domain.food.dto.request.UpdateFoodRequest

interface FoodService {
    fun createFood(request: CreateFoodRequest)
    fun getRandomFood(): List<FoodResponse>
    fun updateFood(request: UpdateFoodRequest)
    fun deleteFood(foodId: Long)
}