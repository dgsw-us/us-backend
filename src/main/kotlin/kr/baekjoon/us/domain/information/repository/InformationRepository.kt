package kr.baekjoon.us.domain.information.repository

import kr.baekjoon.us.domain.information.domain.Information
import org.springframework.data.jpa.repository.JpaRepository

interface InformationRepository: JpaRepository<Information, Long> {
}