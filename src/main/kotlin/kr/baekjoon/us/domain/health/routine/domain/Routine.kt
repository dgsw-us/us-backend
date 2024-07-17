package kr.baekjoon.us.domain.health.routine.domain

import jakarta.persistence.*
import kr.baekjoon.us.domain.user.domain.User

@Entity
data class Routine (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "writer")
    val writer: User,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var doUser: List<Do> = arrayListOf(),
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val exerciseList: List<Exercise> = arrayListOf()
)