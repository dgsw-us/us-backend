package kr.baekjoon.us.domain.user.repository

import kr.baekjoon.us.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUserId(userId: String): User?
}