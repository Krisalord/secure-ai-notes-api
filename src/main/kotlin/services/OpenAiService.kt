package io.github.krisalord.services

import io.github.krisalord.config.AiRequestFailedException
import io.github.krisalord.models.dto.openai.OpenAiMessage
import io.github.krisalord.models.dto.openai.OpenAiRequest
import io.github.krisalord.models.dto.openai.OpenAiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class OpenAiService(private val apiKey: String) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun makeOpenAIAPIRequest(text: String): String {
        try {
            val requestBody = OpenAiRequest(
                model = "gpt-4o-mini",
                messages = listOf(OpenAiMessage("user", text))
            )

            val response: OpenAiResponse = client.post("https://api.openai.com/v1/chat/completions") {
                header(HttpHeaders.Authorization, "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }.body()

            return response.choices.first().message.content
        } catch (e: Exception) {
            throw AiRequestFailedException("AI request failed")
        }
    }
}