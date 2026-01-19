package io.github.krisalord.errors

import io.ktor.server.plugins.statuspages.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.http.*

fun Application.installErrorHandling() {
    install(StatusPages) {

        exception<ValidationException> { call, e ->
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }

        exception<UserAlreadyExistsException> { call, e ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to e.message))
        }

        exception<UserNotFoundException> { call, e ->
            call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))
        }

        exception<InvalidPasswordException> { call, e ->
            call.respond(HttpStatusCode.Unauthorized, mapOf("error" to e.message))
        }

        exception<AuthenticationException> { call, e ->
            call.respond(HttpStatusCode.Unauthorized, mapOf("error" to e.message))
        }

        exception<AuthorizationException> { call, e ->
            call.respond(HttpStatusCode.Forbidden, mapOf("error" to e.message))
        }

        exception<NoNotesException> { call, e ->
            call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
        }

        exception<RateLimitExceededException> { call, e ->
            call.respond(HttpStatusCode.TooManyRequests, mapOf("error" to e.message))
        }

        exception<AiRequestFailedException> { call, _ ->
            call.respond(HttpStatusCode.BadGateway, mapOf("error" to "AI service unavailable"))
        }

        exception<Throwable> { call, _ ->
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Internal server error"))
        }
    }
}