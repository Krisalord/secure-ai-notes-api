package io.github.krisalord.config

sealed class AppException(message: String) : RuntimeException(message)

class ValidationException(message: String) : AppException(message)
class UserAlreadyExistsException(message: String) : AppException(message)
class UserNotFoundException(message: String) : AppException(message)
class InvalidPasswordException(message: String) : AppException(message)

class NoteNotFoundException(message: String) : AppException(message)
class UnauthorizedNoteAccessException(message: String) : AppException(message)

class NoNotesException(message: String) : AppException(message)
class AiRequestFailedException(message: String) : AppException(message)
class RateLimitExceededException(message: String) : AppException(message)
