package io.github.krisalord.services

import io.github.krisalord.models.Note
import io.github.krisalord.repositories.NoteRepository
import io.github.krisalord.security.Sanitizer
import io.github.krisalord.validation.NoteValidation
import org.bson.types.ObjectId

class NoteService(
    private val noteRepository: NoteRepository
) {
    fun getUserNotes(userId: ObjectId): List<Note> {
        return noteRepository.findByUser(userId)
    }

    fun createNote(userId: ObjectId, content: String): Note {
        NoteValidation.validateContent(content)

        val sanitized = Sanitizer.sanitizeText(content)

        val note = Note(
            userId = userId,
            content = sanitized
        )

        noteRepository.create(note)
        return note
    }
}