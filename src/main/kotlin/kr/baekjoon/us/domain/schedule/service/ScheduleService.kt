package kr.baekjoon.us.domain.schedule.service

import kr.baekjoon.us.domain.schedule.dto.CreateScheduleRequest
import kr.baekjoon.us.domain.schedule.dto.ScheduleResponse
import kr.baekjoon.us.domain.schedule.dto.UpdateScheduleRequest
import java.security.Principal

interface ScheduleService {
    fun createSchedule(request: CreateScheduleRequest, principal: Principal)
    fun getSchedule(scheduleId: Long): ScheduleResponse
    fun getScheduleList(): List<ScheduleResponse>
    fun getScheduleList(commonSchedule: Boolean, principal: Principal): List<ScheduleResponse>
    fun updateSchedule(request: UpdateScheduleRequest, principal: Principal)
    fun deleteSchedule(scheduleId: Long, principal: Principal)
}