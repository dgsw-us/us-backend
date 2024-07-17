package kr.baekjoon.us.domain.food.service

import kr.baekjoon.us.domain.food.domain.Food
import kr.baekjoon.us.domain.food.dto.FoodResponse
import kr.baekjoon.us.domain.food.dto.request.CreateFoodRequest
import kr.baekjoon.us.domain.food.dto.request.UpdateFoodRequest
import kr.baekjoon.us.domain.food.repository.FoodRepository
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class FoodServiceImpl (
    private val foodRepository: FoodRepository
): FoodService {
    override fun createFood(request: CreateFoodRequest) {
        val food = Food(
            name = request.name,
            description = request.description,
        )

        foodRepository.save(food)
    }

    override fun getRandomFood(): List<FoodResponse> {
        val foodList = foodRepository.findByRandom()

        return foodList.map {
            FoodResponse(
                id = it.id!!,
                name = it.name,
                description = it.description
            )
        }
    }

    override fun updateFood(
        request: UpdateFoodRequest
    ) {
        val food = foodRepository
            .findByIdOrNull(request.id)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "음식 추천을 찾을 수 없음.")

        foodRepository.save(food.copy(
            name = request.name,
            description = request.description
        ))
    }

    override fun deleteFood(foodId: Long) {
        foodRepository.deleteById(foodId)
    }
}