package com.tosdev.plugins.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*

fun configureKafka(): KafkaConsumer<String, String> {
    val props = Properties().apply {
        put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        put(MAX_POLL_INTERVAL_MS_CONFIG, 300000)
        put(GROUP_ID_CONFIG, "group_1");
        put(AUTO_OFFSET_RESET_CONFIG, "earliest")
    }
    return KafkaConsumer<String, String>(props)
}