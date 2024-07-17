package kr.baekjoon.us.domain.board.repository

import kr.baekjoon.us.domain.board.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}