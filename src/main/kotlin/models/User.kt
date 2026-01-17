package io.github.krisalord.models

import io.github.krisalord.serialization.ObjectIdSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class User(
    @SerialName("_id")
    @Serializable(with = ObjectIdSerializer::class)
    val _id: ObjectId = ObjectId(),
    val email: String,
    val passwordHash: String
)