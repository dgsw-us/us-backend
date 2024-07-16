package kr.baekjoon.us.global.auth.jwt

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
    val refreshToken: String
)
