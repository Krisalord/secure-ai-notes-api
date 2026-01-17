package io.github.krisalord.routes

import io.github.krisalord.repositories.NoteRepository
import io.github.krisalord.services.AiService
import io.github.krisalord.services.OpenAiService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.bson.types.ObjectId

fun Route.aiRoutes(aiService: AiService) {
    authenticate("auth-jwt") {

        post("/ai/summarize") {
            val principal = call.principal<JWTPrincipal>()!!
            val userId = ObjectId(principal.payload.getClaim("userId").asString())

            val summary = aiService.summarizeNotes(userId)
            call.respond(mapOf("summary" to summary))
        }
    }
}