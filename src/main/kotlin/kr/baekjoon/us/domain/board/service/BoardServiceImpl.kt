package kr.baekjoon.us.domain.board.service

import kr.baekjoon.us.domain.board.domain.Board
import kr.baekjoon.us.domain.board.domain.Comment
import kr.baekjoon.us.domain.board.dto.BoardResponse
import kr.baekjoon.us.domain.board.dto.CommentResponse
import kr.baekjoon.us.domain.board.dto.request.CreateBoardRequest
import kr.baekjoon.us.domain.board.dto.request.CreateCommentRequest
import kr.baekjoon.us.domain.board.dto.request.UpdateBoardRequest
import kr.baekjoon.us.domain.board.dto.request.UpdateCommentRequest
import kr.baekjoon.us.domain.board.enums.BoardCategory
import kr.baekjoon.us.domain.board.repository.BoardRepository
import kr.baekjoon.us.domain.board.repository.CommentRepository
import kr.baekjoon.us.domain.user.dto.UserResponse
import kr.baekjoon.us.domain.user.repository.UserRepository
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class BoardServiceImpl (
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
    private val commentRepository: CommentRepository
): BoardService {
    override fun createBoard(request: CreateBoardRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        val board = Board(
            title = request.title,
            description = request.description,
            writer = user,
            category = request.category
        )

        boardRepository.save(board)
    }

    override fun getBoard(boardId: Long): BoardResponse {
        val board = boardRepository
            .findByIdOrNull(boardId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.")

        val userResponse = UserResponse(
            id = board.writer.id!!,
            username = board.writer.username,
            birthDate = board.writer.birthDate,
            userId = board.writer.userId,
            email = board.writer.email
        )

        val commentListResponse = board.comments!!.map {
            val user = UserResponse(
                id = it.writer.id!!,
                username = it.writer.username,
                birthDate = it.writer.birthDate,
                userId = it.writer.userId,
                email = it.writer.email
            )

            CommentResponse(
                id = it.id!!,
                content = it.content,
                writer = user,
                regDate = it.regDate!!,
                modDate = it.modDate!!
            )
        }

        return BoardResponse(
            id = board.id!!,
            title = board.title,
            description = board.description,
            writer = userResponse,
            regDate = board.regDate!!,
            modDate = board.modDate!!,
            comments = commentListResponse,
            category = board.category
        )
    }

    override fun getBoardList(category: BoardCategory?): List<BoardResponse> {
        val boards: List<Board> = if (category == null) {
            boardRepository.findAll()
        } else {
            boardRepository.findByCategory(category)
        }

        return boards.map { board ->
            val userResponse = UserResponse(
                id = board.writer.id!!,
                username = board.writer.username,
                birthDate = board.writer.birthDate,
                userId = board.writer.userId,
                email = board.writer.email
            )

            val commentListResponse = board.comments!!.map {
                val user = UserResponse(
                    id = it.writer.id!!,
                    username = it.writer.username,
                    birthDate = it.writer.birthDate,
                    userId = it.writer.userId,
                    email = it.writer.email
                )

                return@map CommentResponse(
                    id = it.id!!,
                    content = it.content,
                    writer = user,
                    regDate = it.regDate!!,
                    modDate = it.modDate!!
                )
            }

            return@map BoardResponse(
                id = board.id!!,
                title = board.title,
                description = board.description,
                writer = userResponse,
                category = board.category,
                comments = commentListResponse,
                regDate = board.regDate!!,
                modDate = board.modDate!!
            )
        }
    }

    override fun updateBoard(request: UpdateBoardRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        val board = boardRepository
            .findByIdOrNull(request.boardId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "보드를 찾을 수 없습니다")

        if (user.id != board.writer.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없습니다")
        }

        boardRepository.save(board.copy(
            title = request.title,
            description = request.description
        ))
    }

    override fun deleteBoard(boardId: Long, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        val board = boardRepository
            .findByIdOrNull(boardId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "보드를 찾을 수 없습니다")

        if (user.id != board.writer.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없습니다")
        }

        commentRepository.deleteAll(commentRepository.findByBoard(board))
        boardRepository.delete(board)
    }

    override fun createComment(request: CreateCommentRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        val board = boardRepository
            .findByIdOrNull(request.boardId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.")

        val comment = Comment(
            content = request.content,
            writer = user,
            board = board
        )

        commentRepository.save(comment)
    }

    override fun updateComment(request: UpdateCommentRequest, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        val comment = commentRepository
            .findByIdOrNull(request.commentId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "댓글 찾을 수 없습니다.")

        if (comment.writer.id != user.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없습니다.")
        }

        commentRepository.save(comment.copy(
            content = request.content
        ))
    }

    override fun deleteComment(commentId: Long, principal: Principal) {
        val user = userRepository
            .findByUserId(principal.name)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")

        val comment = commentRepository
            .findByIdOrNull(commentId)
            ?: throw BusinessException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.")

        if (user.id == comment.writer.id) {
            throw BusinessException(HttpStatus.FORBIDDEN, "권한이 없습니다")
        }

        commentRepository.delete(comment)
    }
}