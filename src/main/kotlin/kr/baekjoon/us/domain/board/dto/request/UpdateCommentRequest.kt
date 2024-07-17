package kr.baekjoon.us.domain.board.dto.request

data class UpdateCommentRequest (
    val content: String,
    val commentId: Long,
)