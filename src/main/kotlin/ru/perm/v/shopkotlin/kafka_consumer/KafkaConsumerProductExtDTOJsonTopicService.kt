package ru.perm.v.shopkotlin.kafka_consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.stereotype.Component

import ru.perm.v.shopkotlin.extdto.ProductExtDTO

/**
 * Reader from topic "product_ext_dto"
 */
@Component
class KafkaConsumerProductExtDTOJsonTopicService {

    val jsonProductExtDTODeserializer = JsonDeserializer(ProductExtDTO::class.java)
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @KafkaListener(topics = ["product_ext_dto_topic"], groupId = "test_id", properties = ["auto.offset.reset=earliest"])
    fun readFromTopic(json: String): ProductExtDTO {
        val productExtDto = jsonProductExtDTODeserializer.deserialize("", json.toByteArray())
        logger.info(productExtDto.toString())
        return productExtDto
    }
}