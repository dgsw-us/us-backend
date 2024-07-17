package kr.baekjoon.us.domain.health.routine.repository

import kr.baekjoon.us.domain.health.routine.domain.Exercise
import kr.baekjoon.us.domain.health.routine.domain.Routine
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository: JpaRepository<Exercise, Long> {
    fun findByRoutine(routine: Routine): List<Exercise>
}