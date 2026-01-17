package io.github.krisalord.models.dto.openai

import kotlinx.serialization.Serializable

@Serializable
data class OpenAiMessage(
    val role: String,
    val content: String
)

@Serializable
data class OpenAiRequest(
    val model: String,
    val messages: List<OpenAiMessage>
)
