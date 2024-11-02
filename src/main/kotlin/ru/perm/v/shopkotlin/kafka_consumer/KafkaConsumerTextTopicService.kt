package ru.perm.v.shopkotlin.kafka_consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumerTextTopicService {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @KafkaListener(topics = ["text_topic"], groupId = "test_id")
    fun read(message: String) {
        logger.info(message)
    }
}