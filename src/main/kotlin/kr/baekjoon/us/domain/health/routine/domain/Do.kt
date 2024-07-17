package kr.baekjoon.us.domain.health.routine.domain

import jakarta.persistence.*
import kr.baekjoon.us.domain.user.domain.User

@Entity
data class Do (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "user")
    val user: User,
    @ManyToOne
    @JoinColumn(name = "routine")
    val routine: Routine
)