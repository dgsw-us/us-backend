package kr.baekjoon.us.domain.food.repository

import kr.baekjoon.us.domain.food.domain.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FoodRepository: JpaRepository<Food, Long> {
    @Query("SELECT fd FROM Food fd ORDER BY RANDOM() LIMIT 4")
    fun findByRandom(): List<Food>
}