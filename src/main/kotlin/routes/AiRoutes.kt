package io.github.krisalord.routes

import io.github.krisalord.services.AiSummaryService
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.bson.types.ObjectId

fun Route.aiRoutes(aiSummaryService: AiSummaryService) {
    authenticate("auth-jwt") {

        post("/ai/summarize") {
            val principal = call.principal<JWTPrincipal>()!!
            val userId = ObjectId(principal.payload.getClaim("userId").asString())

            val summary = aiSummaryService.summarizeNotes(userId)
            call.respond(mapOf("summary" to summary))
        }
    }
}