package io.github.krisalord.repositories

import com.mongodb.client.MongoCollection
import com.mongodb.client.result.UpdateResult
import io.github.krisalord.models.Note
import org.bson.types.ObjectId
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.setValue

class NoteRepository(
    private val collection: MongoCollection<Note>
) {

    fun create(note: Note): Note {
        collection.insertOne(note)
        return note
    }

    fun findByUser(userId: ObjectId): List<Note> {
        return collection.find(Note::userId eq userId).toList()
    }

    fun findById(noteId: ObjectId): Note? {
        return collection.findOne(Note::_id eq noteId)
    }

    fun updateContent(noteId: ObjectId, content: String): Boolean {
        val result: UpdateResult = collection.updateOne(
            Note::_id eq noteId,
            setValue(Note::content, content)
        )
        return result.matchedCount > 0
    }

    fun delete(noteId: ObjectId): Boolean {
        return collection.deleteOne(Note::_id eq noteId).deletedCount > 0
    }
}