val kotlin_version: String by project
val logback_version: String by project


plugins {
    kotlin("jvm") version "2.2.21"
    id("io.ktor.plugin") version "3.3.2"
    kotlin("plugin.serialization") version "2.2.21"
}

repositories {
    mavenCentral()
}

group = "io.github.krisalord"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    // Ktor core
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-client-cio-jvm")

    // JSON serialization (REQUIRED)
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // MongoDB (KMongo)
    implementation("org.litote.kmongo:kmongo:5.2.0")

    // Password hashing
    implementation("org.mindrot:jbcrypt:0.4")

    // Authentication / JWT
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("io.ktor:ktor-server-core:3.3.2")
    implementation("io.ktor:ktor-server-core:3.3.2")

    // Testing
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // Ktor client core
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-cio:2.3.5") // or OkHttp engine

    // Client serialization
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

    // Kotlinx serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //errors
    implementation("io.ktor:ktor-server-status-pages")
}
