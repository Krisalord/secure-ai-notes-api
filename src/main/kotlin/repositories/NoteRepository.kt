package io.github.krisalord.repositories

import com.mongodb.client.MongoCollection
import io.github.krisalord.models.Note
import org.bson.types.ObjectId
import org.litote.kmongo.eq

class NoteRepository (
    private val collection: MongoCollection<Note>,
) {
    fun create(note: Note) {
        collection.insertOne(note)
    }

    fun findByUser(userId: ObjectId): List<Note> {
        return collection.find(Note::userId eq userId).toList()
    }
}