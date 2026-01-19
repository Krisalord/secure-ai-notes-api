val kotlinVersion = "2.2.21"
val ktorVersion = "3.3.2"
val logbackVersion = "1.4.11"
val kmongoVersion = "5.2.0"
val bcryptVersion = "0.4"
val javaJwtVersion = "4.4.0"
val kotlinxSerializationVersion = "1.6.0"
val ktorClientVersion = "2.3.5"

plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"
    id("io.ktor.plugin") version "3.3.2"
}

group = "io.github.krisalord"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven")
        name = "confluence"
    }
}

dependencies {
    // Ktor server core
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-config-yaml:$ktorVersion")

    // Ktor client
    implementation("io.ktor:ktor-client-core:$ktorClientVersion")
    implementation("io.ktor:ktor-client-cio:$ktorClientVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorClientVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorClientVersion")

    // Content negotiation / JSON
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // MongoDB (KMongo)
    implementation("org.litote.kmongo:kmongo:$kmongoVersion")

    // Password hashing
    implementation("org.mindrot:jbcrypt:$bcryptVersion")

    // Authentication / JWT
    implementation("io.ktor:ktor-server-auth:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jwt:$ktorVersion")
    implementation("com.auth0:java-jwt:$javaJwtVersion")

    // Status Pages (error handling)
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.github.flaxoos:ktor-client-circuit-breaker:2.2.1")
    implementation("io.ktor:ktor-server-core:3.3.2")
    implementation("io.ktor:ktor-server-swagger:3.3.2")
    implementation("io.ktor:ktor-server-core:3.3.2")

    // Testing
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}