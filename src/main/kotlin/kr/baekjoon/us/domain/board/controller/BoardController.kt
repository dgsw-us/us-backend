package kr.baekjoon.us.domain.board.controller

import kr.baekjoon.us.domain.board.dto.BoardResponse
import kr.baekjoon.us.domain.board.dto.request.CreateBoardRequest
import kr.baekjoon.us.domain.board.dto.request.CreateCommentRequest
import kr.baekjoon.us.domain.board.dto.request.UpdateBoardRequest
import kr.baekjoon.us.domain.board.dto.request.UpdateCommentRequest
import kr.baekjoon.us.domain.board.enums.BoardCategory
import kr.baekjoon.us.domain.board.service.BoardService
import kr.baekjoon.us.global.common.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/board")
class BoardController (
    private val boardService: BoardService
) {
    @PostMapping
    fun createBoard(
        @RequestBody request: CreateBoardRequest,
        principal: Principal
    ): BaseResponse<Void> {
        boardService.createBoard(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "게시글 생성 완료"
        )
    }

    @GetMapping("/{boardId}")
    fun getBoard(
        @PathVariable boardId: Long
    ): BaseResponse<BoardResponse> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "게시글 조회 완료",
            data = boardService.getBoard(boardId)
        )
    }

    @GetMapping("/list")
    fun getBoardList(
        @RequestParam(required = false)
        category: BoardCategory?
    ): BaseResponse<List<BoardResponse>> {
        return BaseResponse(
            code = HttpStatus.OK,
            message = "게시글 리스트 조회 완료",
            data = boardService.getBoardList(category)
        )
    }

    @PutMapping
    fun updateBoard(
        request: UpdateBoardRequest,
        principal: Principal
    ): BaseResponse<Void> {
        boardService.updateBoard(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "게시글 업데이트 완료"
        )
    }

    @DeleteMapping("/{boardId}")
    fun deleteBoard(
        @PathVariable
        boardId: Long,
        principal: Principal
    ): BaseResponse<Void> {
        boardService.deleteBoard(boardId, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "게시글 삭제 완료"
        )
    }

    @PostMapping("/comment")
    fun createComment(
        request: CreateCommentRequest,
        principal: Principal
    ): BaseResponse<Void> {
        boardService.createComment(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "댓글 생성 완료"
        )
    }

    @PutMapping("/comment")
    fun updateComment(
        request: UpdateCommentRequest,
        principal: Principal
    ): BaseResponse<Void> {
        boardService.updateComment(request, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "댓글 수정 완료"
        )
    }

    @DeleteMapping("/comment/{commentId}")
    fun deleteComment(
        @PathVariable
        commentId: Long,
        principal: Principal
    ): BaseResponse<Void> {
        boardService.deleteComment(commentId, principal)
        return BaseResponse(
            code = HttpStatus.OK,
            message = "댓글 삭제 완료"
        )
    }
}