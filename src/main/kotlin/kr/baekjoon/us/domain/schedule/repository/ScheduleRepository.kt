package kr.baekjoon.us.domain.schedule.repository

import kr.baekjoon.us.domain.schedule.domain.Schedule
import kr.baekjoon.us.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository: JpaRepository<Schedule, Long> {
    fun findByCommonSchedule(commonSchedule: Boolean): List<Schedule>
    fun findByUser(user: User): List<Schedule>
}