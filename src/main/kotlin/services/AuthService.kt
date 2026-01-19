package io.github.krisalord.services

import io.github.krisalord.config.InvalidPasswordException
import io.github.krisalord.config.JwtConfig
import io.github.krisalord.config.UserAlreadyExistsException
import io.github.krisalord.config.UserNotFoundException
import io.github.krisalord.models.User
import io.github.krisalord.repositories.UserRepository
import io.github.krisalord.validation.AuthValidation

class AuthService(
    private val userRepository: UserRepository,
    private val passwordService: PasswordService
) {
    fun register(email: String, rawPassword: String): User {
        AuthValidation.validateEmail(email)
        AuthValidation.validatePassword(rawPassword)

        if (userRepository.findByEmail(email) != null) {
            throw UserAlreadyExistsException("Email already registered")
        }

        return userRepository.createUser(
            User(
                email = email,
                passwordHash = passwordService.hash(rawPassword)
            )
        )
    }

    fun login(email: String, rawPassword: String): String {
        AuthValidation.validateEmail(email)

        val user = userRepository.findByEmail(email)
            ?: throw UserNotFoundException("User not found")

        if (!passwordService.verify(rawPassword, user.passwordHash)) {
            throw InvalidPasswordException("Invalid credentials")
        }

        return JwtConfig.generateToken(user._id.toHexString())
    }
}