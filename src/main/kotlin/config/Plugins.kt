package io.github.krisalord.config

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun Application.installPlugins() {
    install(ContentNegotiation) {
        json(Json { prettyPrint = true; isLenient = true; ignoreUnknownKeys = true })
    }

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(JwtConfig.verifier())
            validate { cred ->
                cred.payload.getClaim("userId").asString()?.let { JWTPrincipal(cred.payload) }
            }
        }
    }

    installErrorHandling()
}
