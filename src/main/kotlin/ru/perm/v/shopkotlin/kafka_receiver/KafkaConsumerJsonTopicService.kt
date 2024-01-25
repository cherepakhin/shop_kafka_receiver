package ru.perm.v.shopkotlin.kafka_receiver

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.stereotype.Component

import ru.perm.v.shopkotlin.extdto.ProductExtDTO
@Component
/**
 * Reader from "json_topic"
 */
class KafkaConsumerJsonTopicService {

    val jsonProductExtDTODeserializer = JsonDeserializer(ProductExtDTO::class.java)
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    @KafkaListener(topics = ["json_topic"], groupId = "test_id")
    fun read(json: String) {
        val productExtDto = jsonProductExtDTODeserializer.deserialize("", json.toByteArray())
        logger.info(productExtDto.toString())
        //TODO: Continue work
    }
}