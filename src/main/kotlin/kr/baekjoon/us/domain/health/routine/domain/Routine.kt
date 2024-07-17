package kr.baekjoon.us.domain.health.routine.domain

import jakarta.persistence.*
import kr.baekjoon.us.domain.user.domain.User

@Entity
data class Routine (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val routineId: Long? = null,
    val name: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "writer")
    val writer: User,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    var doUser: List<Do> = arrayListOf(),
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    val exerciseList: List<Exercise> = arrayListOf()
)