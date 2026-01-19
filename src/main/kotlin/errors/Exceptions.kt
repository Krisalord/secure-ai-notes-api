package io.github.krisalord.errors

// Base exception for the whole app
sealed class AppException(message: String) : RuntimeException(message)

// Validation errors
class ValidationException(message: String) : AppException(message)

// Authentication And User errors
class UserAlreadyExistsException(message: String) : AppException(message)
class UserNotFoundException(message: String) : AppException(message)
class InvalidPasswordException(message: String) : AppException(message)
class AuthenticationException(message: String) : AppException(message)
class AuthorizationException(message: String) : AppException(message)

// Notes related errors
class NoteNotFoundException(message: String) : AppException(message)
class UnauthorizedNoteAccessException(message: String) : AppException(message)
class NoNotesException(message: String) : AppException(message)

// AI service and rate limit errors
class AiRequestFailedException(message: String) : AppException(message)
class RateLimitExceededException(message: String) : AppException(message)