package kr.baekjoon.us.domain.user.service

import kr.baekjoon.us.domain.user.domain.User
import kr.baekjoon.us.domain.user.dto.UserResponse
import kr.baekjoon.us.domain.user.dto.request.*
import kr.baekjoon.us.domain.user.repository.UserRepository
import kr.baekjoon.us.global.auth.jwt.JwtUtils
import kr.baekjoon.us.global.auth.jwt.TokenInfo
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class UserServiceImpl (
    private val jwtUtils: JwtUtils,
    private val userRepository: UserRepository,
    private val userDetailsService: UserDetailsService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
): UserService {
    override fun login(loginRequest: LoginRequest): TokenInfo {
        val user = userRepository.findByUserId(loginRequest.userId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")
        if (bCryptPasswordEncoder.matches(loginRequest.password, user.password)) {
            val userDetails = userDetailsService.loadUserByUsername(user.userId)
            return jwtUtils.generateToken(userDetails)
        } else {
            throw BusinessException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸음")
        }
    }

    override fun refresh(principal: Principal): TokenInfo {
        val user = userRepository.findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")
        val userDetails = userDetailsService.loadUserByUsername(user.userId)
        return jwtUtils.generateToken(userDetails)
    }

    override fun register(request: CreateUserRequest) {
        val user = User (
            username = request.username,
            birthDate = request.birthDate,
            userId = request.userId,
            email = request.email,
            password = bCryptPasswordEncoder.encode(request.password)
        )

        userRepository.save(user)
    }

    override fun getUser(principal: Principal): UserResponse {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 업승ㅁ")

        return UserResponse(
            id = user.id!!,
            username = user.username,
            birthDate = user.birthDate,
            userId = user.userId,
            email = user.email,
        )
    }

    override fun modifyUser(request: ModifyUserRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")

        userRepository.save(user.copy(
            username = request.username,
            birthDate = request.birthDate,
            userId = request.userId,
            email = request.email
        ))
    }

    override fun modifyPassword(request: ModifyPasswordRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")

        if (bCryptPasswordEncoder.matches(request.beforePassword, user.password)) {
            userRepository.save(user.copy(
                password = bCryptPasswordEncoder.encode(request.afterPassword)
            ))
        }
    }

    override fun deleteUser(principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")

        userRepository.delete(user)
    }
}