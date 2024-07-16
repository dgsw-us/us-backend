package kr.baekjoon.us.domain.user.service

import kr.baekjoon.us.domain.user.dto.request.*
import kr.baekjoon.us.global.auth.jwt.TokenInfo
import java.security.Principal

class UserServiceImpl: UserService {
    override fun login(loginRequest: LoginRequest): TokenInfo {
        TODO("Not yet implemented")
    }

    override fun refresh(principal: Principal): TokenInfo {
        TODO("Not yet implemented")
    }

    override fun register(request: CreateUserRequest) {
        TODO("Not yet implemented")
    }

    override fun getUser(principal: Principal): UserResponse {
        TODO("Not yet implemented")
    }

    override fun modifyUser(request: ModifyUserRequest, principal: Principal) {
        TODO("Not yet implemented")
    }

    override fun modifyPassword(request: ModifyPasswordRequest, principal: Principal) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(principal: Principal) {
        TODO("Not yet implemented")
    }
}