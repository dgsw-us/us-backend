package kr.baekjoon.us.domain.health.routine.controller

import kr.baekjoon.us.domain.health.routine.dto.*
import kr.baekjoon.us.domain.health.routine.service.RoutineService
import kr.baekjoon.us.global.common.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/routine")
class RoutineController (
    private val routineService: RoutineService
) {
    @PostMapping
    fun createRoutine(
        @RequestBody
        request: CreateRoutineRequest,
        principal: Principal
    ): BaseResponse<Void> {
        routineService.createRoutine(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "생성 성공"
        )
    }

    @PostMapping("/do/{routineId}")
    fun createDoRoutine(
        @PathVariable
        routineId: Long,
        principal: Principal
    ): BaseResponse<Void> {
        routineService.createRoutine(routineId, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "0"
        )
    }

    @GetMapping("/my")
    fun getMyRoutineList(
        principal: Principal
    ): BaseResponse<List<RoutineResponse>> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "조회 성공",
            data = routineService.getRoutineList(principal)
        )
    }

    @GetMapping("/all")
    fun getRoutineList(
    ): BaseResponse<List<RoutineResponse>> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "조회 성공",
            data = routineService.getRoutineList()
        )
    }

    @PutMapping
    fun updateRoutine(
        @RequestBody
        request: UpdateRoutineRequest,
        principal: Principal
    ): BaseResponse<Void> {
        routineService.updateRoutine(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "수정 성공"
        )
    }

    @DeleteMapping("/{id}")
    fun deleteRoutine(
        @PathVariable
        id: Long,
        principal: Principal
    ): BaseResponse<Void> {
        routineService.deleteRoutine(id, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "삭제 성공"
        )
    }

    @PostMapping("/exercise")
    fun createExercise(
        @RequestBody
        request: CreateExerciseRequest,
        principal: Principal
    ): BaseResponse<Void> {
        routineService.createExercise(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "0"
        )
    }

    @PutMapping("/exercise")
    fun updateExercise(
        @RequestBody
        request: UpdateExerciseRequest,
        principal: Principal
    ): BaseResponse<Void> {
        routineService.updateExercise(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "0"
        )
    }

    @DeleteMapping("/exercise/{id}")
    fun deleteExercise(
        @PathVariable
        id: Long,
        principal: Principal
    ): BaseResponse<Void> {
        routineService.deleteExercise(id, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "0"
        )
    }
}