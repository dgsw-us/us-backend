package kr.baekjoon.us.domain.board.service

import kr.baekjoon.us.domain.board.dto.BoardResponse
import kr.baekjoon.us.domain.board.dto.request.CreateBoardRequest
import kr.baekjoon.us.domain.board.dto.request.CreateCommentRequest
import kr.baekjoon.us.domain.board.dto.request.UpdateBoardRequest
import kr.baekjoon.us.domain.board.dto.request.UpdateCommentRequest
import kr.baekjoon.us.domain.board.enums.BoardCategory
import kr.baekjoon.us.domain.user.dto.request.CreateUserRequest
import java.security.Principal

interface BoardService {
    fun createBoard(request: CreateBoardRequest, principal: Principal)
    fun getBoard(boardId: Long): BoardResponse
    fun getBoardList(category: BoardCategory?): List<BoardResponse>
    fun updateBoard(request: UpdateBoardRequest, principal: Principal)
    fun deleteBoard(boardId: Long, principal: Principal)

    fun createComment(request: CreateCommentRequest, principal: Principal)
    fun updateComment(request: UpdateCommentRequest, principal: Principal)
    fun deleteComment(commentId: Long, principal: Principal)
}