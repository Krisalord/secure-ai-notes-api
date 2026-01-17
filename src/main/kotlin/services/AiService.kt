package io.github.krisalord.services

import io.github.krisalord.config.NoNotesException
import io.github.krisalord.repositories.NoteRepository
import io.github.krisalord.security.RateLimiter
import org.bson.types.ObjectId

class AiService(
    private val noteRepository: NoteRepository,
    private val openAiService: OpenAiService,
    private val rateLimiter: RateLimiter
) {

    suspend fun summarizeNotes(userId: ObjectId): String {
        rateLimiter.check(userId.toHexString())

        val notes = noteRepository.findByUser(userId)
        if (notes.isEmpty()) {
            throw NoNotesException("No notes to summarize")
        }

        val combinedNotes = notes
            .joinToString("\n")
            .take(6000) // hard safety limit

        return openAiService.makeOpenAIAPIRequest(
            """
            Summarize the following notes by importance:
            $combinedNotes
            """.trimIndent()
        )
    }
}
