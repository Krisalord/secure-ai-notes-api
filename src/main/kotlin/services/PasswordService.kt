package io.github.krisalord.services

import org.mindrot.jbcrypt.BCrypt

class PasswordService {
    fun hash(rawPassword: String): String {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt())
    }

    fun verify(rawPassword: String, hashed: String): Boolean {
        return BCrypt.checkpw(rawPassword, hashed)
    }
}