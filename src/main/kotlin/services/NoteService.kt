package io.github.krisalord.services

import io.github.krisalord.errors.NoteNotFoundException
import io.github.krisalord.errors.UnauthorizedNoteAccessException
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
        NoteValidation.validateNoteContent(content)
        val sanitized = Sanitizer.sanitizeText(content)
        return noteRepository.create(
            Note(
                userId = userId,
                content = sanitized
            )
        )
    }

    fun updateNote(
        userId: ObjectId,
        noteId: ObjectId,
        newContent: String
    ) {
        NoteValidation.validateNoteContent(newContent)

        val note = noteRepository.findById(noteId)
            ?: throw NoteNotFoundException("Note not found")

        if (note.userId != userId) {
            throw UnauthorizedNoteAccessException("Not allowed to modify this note")
        }

        val updatedNote = noteRepository.updateContent(
            noteId,
            Sanitizer.sanitizeText(newContent)
        )

        if (!updatedNote) {
            throw NoteNotFoundException("Failed to update note")
        }
    }

    fun deleteNote(
        userId: ObjectId,
        noteId: ObjectId
    ) {
        val note = noteRepository.findById(noteId)
            ?: throw NoteNotFoundException("Note not found")

        if (note.userId != userId) {
            throw UnauthorizedNoteAccessException("Not allowed to delete this note")
        }

        val deleted = noteRepository.delete(noteId)
        if (!deleted) {
            throw NoteNotFoundException("Failed to delete note")
        }
    }
}