package kr.baekjoon.us.domain.information.service

import kr.baekjoon.us.domain.information.dto.InformationResponse
import kr.baekjoon.us.domain.information.dto.request.CreateInformationRequest
import kr.baekjoon.us.domain.information.dto.request.UpdateInformationRequest
import kr.baekjoon.us.domain.information.enums.InformationCategory
import java.security.Principal

interface InformationService {
    fun createInfo(request: CreateInformationRequest, principal: Principal)
    fun getInfo(informationId: Long): InformationResponse
    fun getInfoList(category: InformationCategory?): List<InformationResponse>
    fun updateInfo(request: UpdateInformationRequest, principal: Principal)
    fun deleteInfo(informationId: Long, principal: Principal)
}