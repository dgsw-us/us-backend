package kr.baekjoon.us.domain.information.domain

import jakarta.persistence.*
import kr.baekjoon.us.domain.user.domain.User
import kr.baekjoon.us.global.common.BaseEntity

@Entity
data class Information(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val informationId: Long? = null,
    val title: String,
    val description: String,
    @ManyToOne
    @JoinColumn(name = "id")
    val writer: User
): BaseEntity()
