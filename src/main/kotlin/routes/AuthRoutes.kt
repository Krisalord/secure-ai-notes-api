package io.github.krisalord.routes

import io.github.krisalord.models.dto.auth.AuthResponse
import io.github.krisalord.models.dto.auth.LoginRequest
import io.github.krisalord.models.dto.auth.RegisterRequest
import io.github.krisalord.services.AuthService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.authRoutes(authService: AuthService) {

    post("/register") {
        val request = call.receive<RegisterRequest>()
        val user = authService.register(request.email, request.password)

        call.respond(
            HttpStatusCode.Created,
            mapOf(
                "id" to user._id.toHexString(),
                "email" to user.email
            )
        )
    }

    post("/login") {
        val request = call.receive<LoginRequest>()
        val token = authService.login(request.email, request.password)

        call.respond(AuthResponse(token))
    }
}
