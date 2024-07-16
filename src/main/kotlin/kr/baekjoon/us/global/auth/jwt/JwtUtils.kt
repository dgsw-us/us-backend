package kr.baekjoon.us.global.auth.jwt

import io.jsonwebtoken.*
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date
import io.jsonwebtoken.security.SignatureException
import kr.baekjoon.us.global.exception.BusinessException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtils (
    private val userDetailsService: UserDetailsService,
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.access}")
    private val accessExpired: Long,
    @Value("\${jwt.refresh}")
    private val refreshExpired: Long
) {
    private val secretKey = SecretKeySpec(
        secret.toByteArray(StandardCharsets.UTF_8),
        Jwts.SIG.HS256.key().build().algorithm
    )

    fun generateToken(
        userDetails: UserDetails
    ): TokenInfo {
        val now = Date().time

        val accessToken = Jwts.builder()
            .header()
            .add("typ", "JWT")
            .and()
            .claim("token-typ", "refresh")
            .subject(userDetails.username)
            .issuer("hun")
            .issuedAt(Date(now))
            .expiration(Date(now + accessExpired))
            .signWith(secretKey)
            .compact()

        val refreshToken = Jwts.builder()
            .header()
            .add("typ", "JWT")
            .and()
            .claim("token-typ", "refresh")
            .subject(userDetails.username)
            .issuer("hun")
            .issuedAt(Date(now))
            .expiration(Date(now + refreshExpired))
            .signWith(secretKey)
            .compact()

        return TokenInfo(
            "Bearer",
            accessToken,
            refreshToken
        )
    }

    fun getAuthentication(token: String): Authentication {
        val userId = verifyToken(token).payload.subject
        val userDetails = userDetailsService.loadUserByUsername(userId)

        return UsernamePasswordAuthenticationToken(userDetails, null, null)
    }

    fun verifyToken(token: String): Jws<Claims> {
        try {
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token.removePrefix("Bearer "))
        } catch (e: ExpiredJwtException) {
            println("1")
            throw BusinessException(HttpStatus.FORBIDDEN, "만료된 토큰입니다.")
        } catch (e: SignatureException) {
            println("2")
            throw BusinessException(HttpStatus.FORBIDDEN, "토큰의 서명이 다릅니다.")
        } catch (e: MalformedJwtException) {
            println("3")
            throw BusinessException(HttpStatus.FORBIDDEN, "올바르지 않은 토큰 형식입니다..")
        } catch (e: UnsupportedJwtException) {
            println("4")
            throw BusinessException(HttpStatus.FORBIDDEN, "지원하지 않는 토큰 형식입니다.")
        } catch (e: Exception) {
            println("5")
            throw BusinessException(HttpStatus.FORBIDDEN, "만료된 토큰입니다.")
        }
    }


}