package io.github.krisalord.models.dto.notes

import kotlinx.serialization.Serializable

@Serializable
data class CreateNoteRequest(
    val content: String
)

@Serializable
data class UpdateNoteRequest(
    val content: String
)
