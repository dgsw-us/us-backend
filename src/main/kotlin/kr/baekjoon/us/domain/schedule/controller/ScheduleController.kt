package kr.baekjoon.us.domain.schedule.controller

import kr.baekjoon.us.domain.schedule.dto.CreateScheduleRequest
import kr.baekjoon.us.domain.schedule.dto.ScheduleResponse
import kr.baekjoon.us.domain.schedule.dto.UpdateScheduleRequest
import kr.baekjoon.us.domain.schedule.service.ScheduleService
import kr.baekjoon.us.global.common.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/schedule")
class ScheduleController (
    private val scheduleService: ScheduleService
) {
    @PostMapping
    fun createSchedule(
        @RequestBody
        createScheduleRequest: CreateScheduleRequest,
        principal: Principal
    ): BaseResponse<Void> {
        scheduleService.createSchedule(createScheduleRequest, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "스케쥴 생성 완료"
        )
    }

    @GetMapping("/{scheduleId}")
    fun getSchedule(@PathVariable scheduleId: Long): BaseResponse<ScheduleResponse> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "스케쥴 조회 성공",
            data = scheduleService.getSchedule(scheduleId)
        )
    }

    @GetMapping("/all/my")
    fun getMyScheduleList(
        @RequestParam commonSchedule: Boolean,
        principal: Principal
    ): BaseResponse<List<ScheduleResponse>> {
         return BaseResponse (
             code = HttpStatus.OK,
             message = "스케쥴 조회 성공",
             data = scheduleService.getScheduleList(commonSchedule, principal)
         )
    }

    @GetMapping("/all")
    fun getScheduleList(
    ): BaseResponse<List<ScheduleResponse>> {
        return BaseResponse (
            code = HttpStatus.OK,
            message = "스케쥴 조회 성공",
            data = scheduleService.getScheduleList()
        )
    }

    @PutMapping
    fun updateSchedule(
        @RequestBody
        updateScheduleRequest: UpdateScheduleRequest,
        principal: Principal
    ): BaseResponse<Void> {
        scheduleService.updateSchedule(updateScheduleRequest, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "스케쥴 업데이트 완료"
        )
    }

    @DeleteMapping("/{scheduleId}")
    fun deleteSchedule(
        @PathVariable scheduleId: Long,
        principal: Principal
    ): BaseResponse<Void> {
        scheduleService.deleteSchedule(scheduleId, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "스케쥴 삭제 성공"
        )
    }
}