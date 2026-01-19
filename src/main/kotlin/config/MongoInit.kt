package io.github.krisalord.config

import io.ktor.server.application.*

fun Application.initInfrastructure() {
    MongoConfig.init(environment)
}
