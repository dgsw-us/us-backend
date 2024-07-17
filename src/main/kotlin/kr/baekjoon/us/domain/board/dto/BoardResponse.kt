package kr.baekjoon.us.domain.board.dto

import kr.baekjoon.us.domain.board.enums.BoardCategory
import kr.baekjoon.us.domain.user.dto.UserResponse
import java.time.LocalDateTime

data class BoardResponse(
    val id: Long,
    val title: String,
    val description: String,
    val writer: UserResponse,
    val category: BoardCategory,
    val comments: List<CommentResponse>,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)
