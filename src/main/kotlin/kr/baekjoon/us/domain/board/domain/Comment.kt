package kr.baekjoon.us.domain.board.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import kr.baekjoon.us.domain.user.domain.User
import kr.baekjoon.us.global.common.BaseEntity

@Entity
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val content: String,
    @ManyToOne
    @JoinColumn(name = "writer")
    val writer: User,
    @ManyToOne
    @JoinColumn(name = "board")
    val board: Board
): BaseEntity()
