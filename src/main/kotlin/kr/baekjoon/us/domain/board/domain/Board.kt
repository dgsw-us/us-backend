package kr.baekjoon.us.domain.board.domain

import jakarta.persistence.*
import kr.baekjoon.us.domain.board.enums.BoardCategory
import kr.baekjoon.us.domain.user.domain.User
import kr.baekjoon.us.global.common.BaseEntity

@Entity
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val title: String,
    val description: String,
    val category: BoardCategory,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    val writer: User,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val comments: List<Comment>? = arrayListOf()
): BaseEntity()
