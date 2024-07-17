package kr.baekjoon.us.domain.food.repository

import kr.baekjoon.us.domain.food.domain.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface FoodRepository: JpaRepository<Food, Long> {
    @Query("SELECT * FROM food ORDER BY RAND() LIMIT 4", nativeQuery = true)
    fun findByRandom(): List<Food>
}