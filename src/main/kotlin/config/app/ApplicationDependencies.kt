package io.github.krisalord.config.app

import io.github.krisalord.config.MongoConfig
import io.github.krisalord.models.Note
import io.github.krisalord.models.User
import io.github.krisalord.repositories.NoteRepository
import io.github.krisalord.repositories.UserRepository
import io.github.krisalord.security.RateLimiter
import io.github.krisalord.services.*
import io.ktor.server.application.Application

fun Application.buildDependencies(): Dependencies {

    val database = MongoConfig.database

    val userRepository =
        UserRepository(database.getCollection("users", User::class.java))

    val noteRepository =
        NoteRepository(database.getCollection("notes", Note::class.java))

    val passwordService = PasswordService()
    val authService = AuthService(userRepository, passwordService)

    val noteService = NoteService(noteRepository)

    val openAiService =
        OpenAiService(environment.config.property("ktor.openai.apiKey").getString())

    val rateLimiter = RateLimiter(
        maxRequests = 5,
        windowSeconds = 60
    )

    val aiService = AiService(
        noteRepository = noteRepository,
        openAiService = openAiService,
        rateLimiter = rateLimiter
    )

    return Dependencies(
        authService = authService,
        noteService = noteService,
        aiService = aiService
    )
}

data class Dependencies(
    val authService: AuthService,
    val noteService: NoteService,
    val aiService: AiService
)