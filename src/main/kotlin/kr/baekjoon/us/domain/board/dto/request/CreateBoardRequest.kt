package kr.baekjoon.us.domain.board.dto.request

import kr.baekjoon.us.domain.board.enums.BoardCategory

data class CreateBoardRequest (
    val title: String,
    val description: String,
    val category: BoardCategory
)