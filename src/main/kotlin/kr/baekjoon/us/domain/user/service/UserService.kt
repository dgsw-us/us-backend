package kr.baekjoon.us.domain.user.service

import kr.baekjoon.us.domain.user.dto.request.*
import kr.baekjoon.us.global.auth.jwt.TokenInfo
import java.security.Principal

interface UserService {
    fun login(loginRequest: LoginRequest): TokenInfo
    fun refresh(principal: Principal): TokenInfo
    fun register(request: CreateUserRequest)
    fun getUser(principal: Principal): UserResponse
    fun modifyUser(request: ModifyUserRequest, principal: Principal)
    fun modifyPassword(request: ModifyPasswordRequest, principal: Principal)
    fun deleteUser(principal: Principal)
}