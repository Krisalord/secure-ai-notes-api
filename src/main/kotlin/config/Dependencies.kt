package io.github.krisalord.config

import io.github.krisalord.models.Note
import io.github.krisalord.models.User
import io.github.krisalord.repositories.NoteRepository
import io.github.krisalord.repositories.UserRepository
import io.github.krisalord.security.PasswordHashing
import io.github.krisalord.security.RateLimiter
import io.github.krisalord.services.*
import io.ktor.server.application.Application

class Dependencies(
    val authService: AuthService,
    val noteService: NoteService,
    val aiSummaryService: AiSummaryService
)

fun Application.buildDependencies(): Dependencies {
    val db = MongoConfig.database

    val userRepo = UserRepository(db.getCollection("users", User::class.java))
    val noteRepo = NoteRepository(db.getCollection("notes", Note::class.java))

    val passwordHashing = PasswordHashing()
    val authService = AuthService(userRepo, passwordHashing)
    val noteService = NoteService(noteRepo)

    val openAiClient = OpenAiClient(
        System.getenv("OPENAI_API_KEY")
    )

    val rateLimiter = RateLimiter(maxRequests = 5, windowSeconds = 60)
    val aiSummaryService = AiSummaryService(noteRepo, openAiClient, rateLimiter)

    return Dependencies(authService, noteService, aiSummaryService)
}