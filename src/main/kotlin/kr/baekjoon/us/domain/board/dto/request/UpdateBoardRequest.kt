package kr.baekjoon.us.domain.board.dto.request

data class UpdateBoardRequest (
    val boardId: Long,
    val title: String,
    val description: String
)