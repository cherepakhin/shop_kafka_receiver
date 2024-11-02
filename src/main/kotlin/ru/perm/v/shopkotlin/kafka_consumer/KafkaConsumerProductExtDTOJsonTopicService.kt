package ru.perm.v.shopkotlin.kafka_consumer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.stereotype.Component

import ru.perm.v.shopkotlin.extdto.ProductExtDTO

/**
 * Reader from topic "product_ext_dto_topic"
 */
@Component
class KafkaConsumerProductExtDTOJsonTopicService {
    var count = 0L
    val jsonProductExtDTODeserializer = JsonDeserializer(ProductExtDTO::class.java)
    val mapper = jacksonObjectMapper()
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @KafkaListener(topics = ["product_ext_dto_topic"], groupId = "test_id",
        properties = ["auto.offset.reset=earliest","fetch.max.bytes=20971520"])
    fun readFromTopic(json: String): ProductExtDTO {
        logger.info("read from product_ext_dto_topic: $json")
        val productExtDto= mapper.readValue(json, ProductExtDTO::class.java)
        log(productExtDto)
        return productExtDto
    }

    fun log(productExtDTO: ProductExtDTO) {
        val logString= String.format("%s) %s", count++, productExtDTO)
        logger.info(logString)
    }
}