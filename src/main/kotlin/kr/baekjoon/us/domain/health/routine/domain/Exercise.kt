package kr.baekjoon.us.domain.health.routine.domain

import jakarta.persistence.*
import kr.baekjoon.us.domain.user.domain.User

@Entity
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "writer")
    val writer: User,
    @ManyToOne
    @JoinColumn(name = "routine")
    val routine: Routine,
)
