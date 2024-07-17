package kr.baekjoon.us.domain.information.service

import kr.baekjoon.us.domain.information.domain.Information
import kr.baekjoon.us.domain.information.dto.InformationResponse
import kr.baekjoon.us.domain.information.dto.request.CreateInformationRequest
import kr.baekjoon.us.domain.information.dto.request.UpdateInformationRequest
import kr.baekjoon.us.domain.information.enums.InformationCategory
import kr.baekjoon.us.domain.information.repository.InformationRepository
import kr.baekjoon.us.domain.user.dto.UserResponse
import kr.baekjoon.us.domain.user.repository.UserRepository
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class InformationServiceImpl (
    private val userRepository: UserRepository,
    private val informationRepository: InformationRepository
): InformationService {
    override fun createInfo(request: CreateInformationRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없음")

        val information = Information (
            title = request.title,
            description = request.description,
            writer = user,
            category = request.category
        )

        informationRepository.save(information)
    }

    override fun getInfo(informationId: Long): InformationResponse {
        val information = informationRepository
            .findByIdOrNull(informationId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "정보를 찾을 수 없습니다.")

        val userResponse = UserResponse(
            id = information.writer.id!!,
            username = information.writer.username,
            birthDate = information.writer.birthDate,
            userId = information.writer.userId,
            email = information.writer.email
        )

        return InformationResponse(
            informationId = information.informationId!!,
            title = information.title,
            description = information.description,
            writer = userResponse,
            regDate = information.regDate!!,
            modDate = information.modDate!!,
            category = information.category
        )
    }

    override fun getInfoList(category: InformationCategory?): List<InformationResponse> {
        val informationList = if (category == null) {
            informationRepository.findAll()
        } else {
            informationRepository.findByCategory(category)
        }

        return informationList.map { information ->
            val userResponse = UserResponse(
                id = information.writer.id!!,
                username = information.writer.username,
                birthDate = information.writer.birthDate,
                userId = information.writer.userId,
                email = information.writer.email
            )

            return@map InformationResponse(
                informationId = information.informationId!!,
                title = information.title,
                description = information.description,
                writer = userResponse,
                regDate = information.regDate!!,
                modDate = information.modDate!!,
                category = information.category
            )
        }
    }

    override fun updateInfo(
        request: UpdateInformationRequest,
        principal: Principal
    ) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        val information = informationRepository
            .findByIdOrNull(request.informationId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "정보를 찾을 수 없습니다.")

        if (information.writer.id!! != user.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없습니다.")
        }

        informationRepository.save(information.copy(
            title = request.title,
            description = request.description
        ))
    }

    override fun deleteInfo(informationId: Long, principal: Principal) {
        val information = informationRepository
            .findByIdOrNull(informationId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "정보를 찾을 수 없습니다.")

        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        if (information.writer.id != user.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없습니다.")
        }

        informationRepository.delete(information)
    }
}