package com.tosdev.plugins.kafka

import com.tosdev.plugins.emitBBQuotes
import com.tosdev.plugins.emitRandomSourceQuotes
import kotlinx.coroutines.flow.map
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("com.tosdev.KafkaProducer")

suspend fun sendBbQuotes(producer: KafkaProducer<String, String>) {
    emitBBQuotes().map { quoteStr ->
        ProducerRecord("default", "bb-quotes", quoteStr)
    }.collect { record ->
        producer.send(record)
        logger.info("bb record sent to kafka")
    }
}

suspend fun sendRandomSourceQuotes(producer: KafkaProducer<String, String>) {
    emitRandomSourceQuotes().map { quoteStr ->
        ProducerRecord("default", "random-source-quotes", quoteStr)
    }.collect { record ->
        producer.send(record)
        logger.info("random source record sent to kafka")
    }
}
