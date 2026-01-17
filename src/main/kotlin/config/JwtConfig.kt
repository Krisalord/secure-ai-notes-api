package io.github.krisalord.config

import java.util.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

object JwtConfig {
    private const val secret = "secure-secret-key"
    private const val issuer = "secure-notes"
    private const val validityInMs = 3600000 * 24

    private val algorithm = Algorithm.HMAC256(secret)

    fun generateToken(userId: String): String =
        JWT.create()
            .withIssuer(issuer)
            .withClaim("userId", userId)
            .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
            .sign(algorithm)

    fun verifier() =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
}