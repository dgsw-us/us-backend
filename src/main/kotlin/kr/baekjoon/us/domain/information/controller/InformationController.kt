package kr.baekjoon.us.domain.information.controller

import kr.baekjoon.us.domain.information.dto.InformationResponse
import kr.baekjoon.us.domain.information.dto.request.CreateInformationRequest
import kr.baekjoon.us.domain.information.dto.request.UpdateInformationRequest
import kr.baekjoon.us.domain.information.enums.InformationCategory
import kr.baekjoon.us.domain.information.service.InformationService
import kr.baekjoon.us.global.common.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/info")
class InformationController (
    private val informationService: InformationService
) {
    @PostMapping
    fun createInfo(
        @RequestBody
        request: CreateInformationRequest,
        principal: Principal
    ): BaseResponse<Void> {
        informationService.createInfo(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "정보 생성 완료"
        )
    }

    @GetMapping("/{informationId}")
    fun getInfo(
        @PathVariable
        informationId: Long
    ): BaseResponse<InformationResponse> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "정보 조회 완료",
            data = informationService.getInfo(informationId)
        )
    }

    @GetMapping("/list")
    fun getInfoList(
        @RequestParam category: InformationCategory
    ): BaseResponse<List<InformationResponse>> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "정보 리스트 조회 완료",
            data = informationService.getInfoList(category)
        )
    }

    @PutMapping
    fun updateInfo(
        @RequestBody
        request: UpdateInformationRequest,
        principal: Principal
    ): BaseResponse<Void> {
        informationService.updateInfo(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "정보 수정 완료"
        )
    }

    @DeleteMapping("/{informationId}")
    fun deleteInfo(
        @PathVariable
        informationId: Long,
        principal: Principal
    ): BaseResponse<Void> {
        informationService.deleteInfo(informationId, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "정보 삭제 완료"
        )
    }
}