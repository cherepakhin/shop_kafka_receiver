package ru.perm.v.shopkotlin.kafka_receiver

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
/**
 * Reader from "json_topic"
 */
class KafkaConsumerJsonTopicService {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    @KafkaListener(topics = ["json_topic"], groupId = "test_id")
    fun read(message: String) {
        logger.info(message)
    }
}