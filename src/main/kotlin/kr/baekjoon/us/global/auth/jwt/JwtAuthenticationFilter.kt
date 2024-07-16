package kr.baekjoon.us.global.auth.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val jwtUtils: JwtUtils
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("asdf")
        request.getHeader("Authorization")
            ?.let { token ->
                println("1")
                println(token)
                if (token.startsWith("Bearer ")) {
                    println("asdlfkj")
                    println(jwtUtils.getAuthentication(token))
                    SecurityContextHolder.getContext().authentication = jwtUtils.getAuthentication(token)
                }
            }
        doFilter(request, response, filterChain)
    }
}