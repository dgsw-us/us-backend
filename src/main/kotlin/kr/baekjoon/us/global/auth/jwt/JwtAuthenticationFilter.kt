package kr.baekjoon.us.global.auth.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.baekjoon.us.global.common.BaseResponse
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val jwtUtils: JwtUtils,
    private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        println("asdf")
        request.getHeader("Authorization")
            ?.let { token ->
                println("1")
                println(token)
                if (token.startsWith("Bearer ")) {
                    println("asdlfkj")
                    try {
                        println(jwtUtils.getAuthentication(token))
                        SecurityContextHolder.getContext().authentication = jwtUtils.getAuthentication(token)
                    } catch (e: BusinessException) {
                        e.printStackTrace()

                        response.status = 403
                        response.contentType = MediaType.APPLICATION_JSON_VALUE

                        response.writer.write(
                            objectMapper.writeValueAsString(BaseResponse<Void> (
                                code = HttpStatus.FORBIDDEN,
                                message = "Authentication failed",
                            ))
                        )
                    }
                }
            }
        doFilter(request, response, filterChain)
    }
}