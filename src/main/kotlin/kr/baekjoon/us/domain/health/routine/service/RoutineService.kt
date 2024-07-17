package kr.baekjoon.us.domain.health.routine.service

import kr.baekjoon.us.domain.health.routine.dto.*
import java.security.Principal

interface RoutineService {
    fun createRoutine(request: CreateRoutineRequest, principal: Principal)
    fun createRoutine(routineId: Long, principal: Principal)
    fun getRoutineList(principal: Principal): List<RoutineResponse>
    fun getRoutineList(): List<RoutineResponse>
    fun updateRoutine(request: UpdateRoutineRequest, principal: Principal)
    fun deleteRoutine(routineId: Long, principal: Principal)

    fun createExercise(request: CreateExerciseRequest, principal: Principal)
    fun updateExercise(request: UpdateExerciseRequest, principal: Principal)
    fun deleteExercise(exerciseId: Long, principal: Principal)
}