package io.github.krisalord.repositories

import com.mongodb.client.MongoCollection
import io.github.krisalord.models.User
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

class UserRepository(
    private val collection: MongoCollection<User>
) {

    fun createUser(user: User): User {
        collection.insertOne(user)
        return user
    }

    fun findByEmail(email: String): User? {
        return collection.findOne(User::email eq email)
    }
}