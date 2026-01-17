package io.github.krisalord.config.app

import io.github.krisalord.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.registerRoutes(deps: Dependencies) {
    routing {
        authRoutes(deps.authService)
        noteRoutes(deps.noteService)
        aiRoutes(deps.aiService)
    }
}