package kr.baekjoon.us.domain.user.controller

import kr.baekjoon.us.domain.user.dto.UserResponse
import kr.baekjoon.us.domain.user.dto.request.*
import kr.baekjoon.us.domain.user.service.UserService
import kr.baekjoon.us.global.auth.jwt.TokenInfo
import kr.baekjoon.us.global.common.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController (
    private val userService: UserService
) {
    @PostMapping("/login")
    fun login(
        @RequestBody
        request: LoginRequest
    ): BaseResponse<TokenInfo> {
        return BaseResponse(
            HttpStatus.OK,
            "로그인 완료",
            userService.login(request)
        )
    }

    @GetMapping("/refresh")
    fun refresh(
        principal: Principal
    ): BaseResponse<TokenInfo> {
        return BaseResponse(
            HttpStatus.OK,
            "재발급 완료",
            userService.refresh(principal)
        )
    }

    @PostMapping
    fun register(
        @RequestBody
        request: CreateUserRequest
    ): BaseResponse<Void> {
        userService.register(request)
        return BaseResponse(
            HttpStatus.OK,
            "회원가입 완료"
        )
    }

    @GetMapping
    fun getUser(
        principal: Principal
    ): BaseResponse<UserResponse> {
        return BaseResponse(
            HttpStatus.OK,
            "유저 정보 조회 성공",
            userService.getUser(principal)
        )
    }

    @PutMapping
    fun modifyUser(
        @RequestBody
        request: ModifyUserRequest,
        principal: Principal
    ): BaseResponse<Void> {
        userService.modifyUser(request, principal)
        return BaseResponse(
            HttpStatus.OK,
            "유저 정보 수정 완료",
        )
    }

    @PatchMapping("/password")
    fun modifyPassword(
        @RequestBody
        request: ModifyPasswordRequest,
        principal: Principal
    ): BaseResponse<Void> {
        userService.modifyPassword(request, principal)
        return BaseResponse(
            HttpStatus.OK,
            "비밀번호 수정 완료"
        )
    }

    @DeleteMapping
    fun deleteUser(
        principal: Principal
    ): BaseResponse<Void> {
        userService.deleteUser(principal)
        return BaseResponse(
            HttpStatus.OK,
            "유저 삭제 완료"
        )
    }
}