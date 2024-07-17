package kr.baekjoon.us.domain.information.repository

import kr.baekjoon.us.domain.information.domain.Information
import kr.baekjoon.us.domain.information.enums.InformationCategory
import org.springframework.data.jpa.repository.JpaRepository

interface InformationRepository: JpaRepository<Information, Long> {
    fun findByCategory(category: InformationCategory): List<Information>
}