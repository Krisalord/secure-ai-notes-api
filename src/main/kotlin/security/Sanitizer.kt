package io.github.krisalord.security

object Sanitizer {
    fun sanitizeText(input: String): String {
        return input
            .trim()
            .replace(Regex("<.*?>"), "")
    }
}