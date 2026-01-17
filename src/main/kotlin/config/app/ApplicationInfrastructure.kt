package io.github.krisalord.config.app

import io.github.krisalord.config.MongoConfig
import io.ktor.server.application.*

fun Application.initInfrastructure() {
    MongoConfig.init(environment)
}
