package kr.baekjoon.us.domain.schedule.domain

import jakarta.persistence.*
import kr.baekjoon.us.domain.user.domain.User
import java.time.LocalDateTime

@Entity
data class Schedule (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val commonSchedule: Boolean,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user")
    val user: User
)