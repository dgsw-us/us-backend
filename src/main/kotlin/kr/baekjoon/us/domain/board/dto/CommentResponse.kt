package kr.baekjoon.us.domain.board.dto

import kr.baekjoon.us.domain.user.domain.User
import kr.baekjoon.us.domain.user.dto.UserResponse
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val writer: UserResponse,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)