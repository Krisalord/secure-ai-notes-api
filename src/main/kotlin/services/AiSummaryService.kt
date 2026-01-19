package io.github.krisalord.services

import io.github.krisalord.errors.NoNotesException
import io.github.krisalord.repositories.NoteRepository
import io.github.krisalord.security.RateLimiter
import org.bson.types.ObjectId

class AiSummaryService(
    private val mongoNoteRepository: NoteRepository,
    private val openAiClient: OpenAiClient,
    private val rateLimiter: RateLimiter
) {

    suspend fun summarizeNotes(userId: ObjectId): String {
        rateLimiter.check(userId.toHexString())

        val notes = mongoNoteRepository.findByUser(userId)
        if (notes.isEmpty()) {
            throw NoNotesException("No notes to summarize")
        }

        val combinedNotes = notes
            .joinToString("\n")
            .take(6000) // Char limit for request

        return openAiClient.makeOpenAIAPIRequest(
            """
            DO NOT disclose ID of the users nor any other information about the notes except for the contents of them
            Summarize the following notes by importance:
            $combinedNotes
            """.trimIndent()
        )
    }
}