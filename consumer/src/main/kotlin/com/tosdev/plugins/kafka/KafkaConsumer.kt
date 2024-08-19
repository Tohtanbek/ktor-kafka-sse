package com.tosdev.plugins.kafka

import kotlinx.coroutines.flow.flow
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration.ofMillis


suspend fun consumeQuotes(consumer: KafkaConsumer<String, String>, consumerId: Int) = flow {
    consumer.subscribe(listOf("default"))
    while (true) {
        val records =
            consumer.poll(ofMillis(100))
        for (record in records) {
            emit("consumer: $consumerId key: ${record.key()} value: ${record.value()}")
        }
    }
}