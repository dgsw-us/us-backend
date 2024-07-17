package kr.baekjoon.us.domain.food.controller

import kr.baekjoon.us.domain.food.dto.FoodResponse
import kr.baekjoon.us.domain.food.dto.request.CreateFoodRequest
import kr.baekjoon.us.domain.food.dto.request.UpdateFoodRequest
import kr.baekjoon.us.domain.food.service.FoodService
import kr.baekjoon.us.global.common.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/food")
class FoodController (
    private val foodService: FoodService
) {
    @PostMapping
    fun createFood(
        @RequestBody
        request: CreateFoodRequest
    ): BaseResponse<List<Void>> {
        foodService.createFood(request)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "Successfully created food"
        )
    }

    @GetMapping
    fun getFoodRandom(
    ): BaseResponse<List<FoodResponse>> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "음식 추천 생성 성공",
            data = foodService.getRandomFood()
        )
    }

    @PutMapping
    fun updateFood(
        @RequestBody
        request: UpdateFoodRequest
    ): BaseResponse<Void> {
        foodService.updateFood(request)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "Successfully updated food"
        )
    }

    @DeleteMapping("/{id}")
    fun deleteFood(
        @PathVariable id: Long
    ): BaseResponse<Void> {
        foodService.deleteFood(id)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "Successfully deleted food"
        )
    }
}