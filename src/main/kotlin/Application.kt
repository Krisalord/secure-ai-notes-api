package io.github.krisalord

import io.github.krisalord.config.*
import io.ktor.server.application.Application

fun Application.module() {
    installPlugins()
    initInfrastructure()
    val dependencies = buildDependencies()
    registerRoutes(dependencies)
}