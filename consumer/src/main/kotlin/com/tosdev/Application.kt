package com.tosdev

import com.tosdev.plugins.kafka.configureKafka
import com.tosdev.plugins.kafka.consumeQuotes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.sse.*
import io.ktor.sse.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

fun main() {
    embeddedServer(Netty, port = 5050) {
        val consumer1 = configureKafka()
        val consumer2 = configureKafka()
        val sharedFlow1 = MutableSharedFlow<String>(replay = 1)
        val sharedFlow2 = MutableSharedFlow<String>(replay = 1)
        launch {
            consumeQuotes(consumer1, 1).collect { value ->
                println(value)
                sharedFlow1.emit(value)
            }
        }
        launch {
            consumeQuotes(consumer2, 2).collect { value ->
                println(value)
                sharedFlow2.emit(value)
            }
        }

        install(SSE)
        install(CORS) {
            allowHost(host = "localhost:4200", schemes = listOf("http"))
        }
        routing {
            sse("/bb-quotes") {
                sharedFlow1.asSharedFlow().collect { el ->
                    send(ServerSentEvent("this is SSE #$el"))
                }
            }
            sse("/random-quotes") {
                sharedFlow2.asSharedFlow().collect { el ->
                    send(ServerSentEvent("this is SSE #$el"))
                }
            }
        }
    }.start(wait = true)
}