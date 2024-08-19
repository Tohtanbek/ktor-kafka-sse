package com.tosdev.plugins

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.utils.io.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("com.tosdev.ClientRunner")
val client = HttpClient(CIO)


suspend fun emitBBQuotes(): Flow<String> {
    val uri = "https://api.breakingbadquotes.xyz/v1/quotes"
    return getQuotesFlow(uri)
}

suspend fun emitRandomSourceQuotes(): Flow<String> {
    val uri = "https://quotes-api-self.vercel.app/quote"
    return getQuotesFlow(uri)
}

private suspend fun getQuotesFlow(uri: String): Flow<String> = flow {
    while (true) {
        client.get(uri).bodyAsChannel().apply {
            readUTF8Line()?.let { emit(it) } ?: run { logger.error("No quote value received") }
        }
        delay(1000)
    }
}
