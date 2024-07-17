package kr.baekjoon.us.domain.board.dto.request

data class CreateCommentRequest (
    val content: String,
    val boardId: Long
)