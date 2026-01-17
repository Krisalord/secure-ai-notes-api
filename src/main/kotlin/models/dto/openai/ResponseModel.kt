package io.github.krisalord.models.dto.openai

import kotlinx.serialization.Serializable

@Serializable
data class OpenAiChoiceMessage(
    val role: String,
    val content: String
)

@Serializable
data class OpenAiChoice(
    val message: OpenAiChoiceMessage
)

@Serializable
data class OpenAiResponse(
    val choices: List<OpenAiChoice>
)
