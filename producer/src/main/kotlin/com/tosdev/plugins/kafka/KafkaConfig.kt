package com.tosdev.plugins.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig.*
import java.util.*

fun configureKafka(): KafkaProducer<String, String> {
    val props = Properties().apply {
        put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        put(LINGER_MS_CONFIG, 1)
        put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    }
    return KafkaProducer<String,String>(props)
}
