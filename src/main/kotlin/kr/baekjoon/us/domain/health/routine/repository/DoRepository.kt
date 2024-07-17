package kr.baekjoon.us.domain.health.routine.repository

import kr.baekjoon.us.domain.health.routine.domain.Do
import kr.baekjoon.us.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface DoRepository: JpaRepository<Do, Long> {
    fun findByUser(user: User): List<Do>
}