package io.github.krisalord.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import io.ktor.server.application.*
import org.litote.kmongo.KMongo

object MongoConfig {
    lateinit var client: MongoClient
        private set

    lateinit var database: MongoDatabase
        private set

    fun init(environment: ApplicationEnvironment) {
        val uri = System.getenv("KTOR_MONGO_URI")
        client = KMongo.createClient(uri)
        database = client.getDatabase("secure-ai-notes-api")
    }
}