package io.github.krisalord

import io.github.krisalord.config.app.buildDependencies
import io.github.krisalord.config.app.initInfrastructure
import io.github.krisalord.config.app.installPlugins
import io.github.krisalord.config.app.registerRoutes
import io.ktor.server.application.Application

fun Application.module() {

    installPlugins()
    initInfrastructure()

    val dependencies = buildDependencies()

    registerRoutes(dependencies)
}
