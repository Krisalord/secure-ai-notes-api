package io.github.krisalord.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

object MongoConfig {
    lateinit var client: MongoClient
        private set

    lateinit var database: MongoDatabase
        private set

    fun init(uri: String) {
        client = KMongo.createClient(uri)
        database = client.getDatabase("secure-ai-notes-api")
    }
}
