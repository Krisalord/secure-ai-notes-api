package io.github.krisalord.config.app

import io.github.krisalord.config.JwtConfig
import io.github.krisalord.config.installErrorHandling
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun Application.installPlugins() {

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JwtConfig.verifier())
            validate { credential ->
                val userId = credential.payload.getClaim("userId").asString()
                if (userId != null) JWTPrincipal(credential.payload) else null
            }
        }
    }

    installErrorHandling()
}
