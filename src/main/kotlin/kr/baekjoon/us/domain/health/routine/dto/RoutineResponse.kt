package kr.baekjoon.us.domain.health.routine.dto

data class RoutineResponse (
    val id: Long,
    val name: String,
    val exerciseList: List<ExerciseResponse>,
)