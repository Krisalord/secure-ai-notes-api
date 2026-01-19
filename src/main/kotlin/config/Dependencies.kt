package io.github.krisalord.config

import io.github.krisalord.models.Note
import io.github.krisalord.models.User
import io.github.krisalord.repositories.NoteRepository
import io.github.krisalord.repositories.UserRepository
import io.github.krisalord.security.RateLimiter
import io.github.krisalord.services.*
import io.ktor.server.application.Application

class Dependencies(
    val authService: AuthService,
    val noteService: NoteService,
    val aiService: AiService
)

fun Application.buildDependencies(): Dependencies {
    val db = MongoConfig.database

    val userRepo = UserRepository(db.getCollection("users", User::class.java))
    val noteRepo = NoteRepository(db.getCollection("notes", Note::class.java))

    val passwordService = PasswordService()
    val authService = AuthService(userRepo, passwordService)
    val noteService = NoteService(noteRepo)

    val openAiService = OpenAiService(
        environment.config.property("ktor.openai.apiKey").getString()
    )

    val rateLimiter = RateLimiter(maxRequests = 5, windowSeconds = 60)
    val aiService = AiService(noteRepo, openAiService, rateLimiter)

    return Dependencies(authService, noteService, aiService)
}
