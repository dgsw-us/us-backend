package kr.baekjoon.us.domain.schedule.dto

import java.time.LocalDateTime

data class UpdateScheduleRequest(
    val id: Long,
    val name: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val commonSchedule: Boolean
)
