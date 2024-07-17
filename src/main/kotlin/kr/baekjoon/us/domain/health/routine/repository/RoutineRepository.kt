package kr.baekjoon.us.domain.health.routine.repository

import kr.baekjoon.us.domain.health.routine.domain.Routine
import kr.baekjoon.us.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RoutineRepository: JpaRepository<Routine, Long> {
    fun findByWriter(writer: User): List<Routine>
}