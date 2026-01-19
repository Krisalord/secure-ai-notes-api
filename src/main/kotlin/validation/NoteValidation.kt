package io.github.krisalord.validation

import io.github.krisalord.errors.ValidationException

object NoteValidation {

    private const val minLength = 1
    private const val maxLenght = 2000

    fun validateNoteContent(content: String) {
        if (content.length < minLength){
            throw ValidationException("Note content cannot be empty")
        }
        if (content.length > maxLenght){
            throw ValidationException("Note content is too long")
        }
    }
}