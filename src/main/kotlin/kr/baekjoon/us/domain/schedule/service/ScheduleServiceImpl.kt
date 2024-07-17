package kr.baekjoon.us.domain.schedule.service

import kr.baekjoon.us.domain.schedule.domain.Schedule
import kr.baekjoon.us.domain.schedule.dto.CreateScheduleRequest
import kr.baekjoon.us.domain.schedule.dto.ScheduleResponse
import kr.baekjoon.us.domain.schedule.dto.UpdateScheduleRequest
import kr.baekjoon.us.domain.schedule.repository.ScheduleRepository
import kr.baekjoon.us.domain.user.repository.UserRepository
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class ScheduleServiceImpl (
    private val scheduleRepository: ScheduleRepository,
    private val userRepository: UserRepository
): ScheduleService {
    override fun createSchedule(request: CreateScheduleRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "스케줄을 찾을 수 없음")
        val schedule = Schedule(
            name = request.name,
            description = request.description,
            startTime = request.startTime,
            endTime = request.endTime,
            commonSchedule = request.commonSchedule,
            user = user
        )

        scheduleRepository.save(schedule)
    }

    override fun getSchedule(scheduleId: Long): ScheduleResponse {
        val schedule = scheduleRepository
            .findByIdOrNull(scheduleId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없음")

        return ScheduleResponse(
            id = schedule.id!!,
            name = schedule.name,
            description = schedule.description,
            startTime = schedule.startTime,
            endTime = schedule.endTime,
            commonSchedule = schedule.commonSchedule
        )
    }

    override fun getScheduleList(): List<ScheduleResponse> {
        val scheduleList = scheduleRepository.findByCommonSchedule(true)

        return scheduleList.map {
            ScheduleResponse(
                id = it.id!!,
                name = it.name,
                description = it.description,
                startTime = it.startTime,
                endTime = it.endTime,
                commonSchedule = it.commonSchedule
            )
        }
    }

    override fun getScheduleList(commonSchedule: Boolean, principal: Principal): List<ScheduleResponse> {
        val response = ArrayList<ScheduleResponse>()
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        if (commonSchedule) {
            response.addAll(scheduleRepository
                .findByCommonSchedule(true)
                .map { ScheduleResponse(
                    id = it.id!!,
                    name = it.name,
                    description = it.description,
                    startTime = it.startTime,
                    endTime = it.endTime,
                    commonSchedule = it.commonSchedule
                ) })
        }
        response.addAll(scheduleRepository
            .findByUser(user)
            .map { ScheduleResponse(
                id = it.id!!,
                name = it.name,
                description = it.description,
                startTime = it.startTime,
                endTime = it.endTime,
                commonSchedule = it.commonSchedule
        )}  )

        return response
    }

    override fun updateSchedule(request: UpdateScheduleRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val schedule = scheduleRepository
            .findByIdOrNull(request.id)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "스케쥴을 찾을 수 없음.")

        if (schedule.user.id != user.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없음")
        }

        scheduleRepository.save(schedule.copy(
            startTime = request.startTime,
            endTime = request.endTime,
            commonSchedule = request.commonSchedule,
            name = request.name,
            description = request.description
        ))
    }

    override fun deleteSchedule(scheduleId: Long, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음.")

        val schedule = scheduleRepository
            .findByIdOrNull(scheduleId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "스케쥴을 찾을 수 없음.")

        if (user.id != schedule.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없음.")
        }

        scheduleRepository.delete(schedule)
    }
}