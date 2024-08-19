val kotlin_version: String by project
val logback_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.0.10"
    id("io.ktor.plugin") version "2.3.12"
}

group = "com.tosdev"
version = "0.0.1"

application {
    mainClass.set("com.tosdev.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")

    implementation("org.apache.kafka:kafka-clients:3.5.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
