package kr.baekjoon.us.domain.board.repository

import kr.baekjoon.us.domain.board.domain.Board
import kr.baekjoon.us.domain.board.enums.BoardCategory
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
    fun findByCategory(category: BoardCategory): List<Board>
}