package kr.baekjoon.us.domain.user.service

import kr.baekjoon.us.domain.user.domain.UserDetailsImpl
import kr.baekjoon.us.domain.user.repository.UserRepository
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl (
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        println("Loading user by username: $username")
        val user = userRepository.findByUserId(username!!)
            ?:throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")

        println(user)

        return UserDetailsImpl(user)
    }
}