package com.tosdev

import com.tosdev.plugins.kafka.configureKafka
import com.tosdev.plugins.kafka.sendBbQuotes
import com.tosdev.plugins.kafka.sendRandomSourceQuotes
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val kafkaProducer = configureKafka()
    val cor1 = launch { sendBbQuotes(kafkaProducer) }
    val cor2 = launch { sendRandomSourceQuotes(kafkaProducer) }
    cor1.join()
    cor2.join()
}

