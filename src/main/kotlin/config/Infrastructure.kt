package io.github.krisalord.config

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.github.krisalord.routes.*

fun Application.initInfrastructure() {
    MongoConfig.init(environment)
}

fun Application.registerRoutes(deps: Dependencies) {
    routing {
        authRoutes(deps.authService)
        noteRoutes(deps.noteService)
        aiRoutes(deps.aiSummaryService)
    }
}
