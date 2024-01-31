package ru.perm.v.shopkotlin.kafka_consumer

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.perm.v.shopkotlin.extdto.ProductExtDTO

class KafkaConsumerProductExtDTOJsonTopicServiceTest {

    @Test
    fun readFromTopic() {
        val consumerService = KafkaConsumerProductExtDTOJsonTopicService()
        val json = "{\"n\":10,\"name\":\"NAME_10\",\"groupDtoN\":100}"

        val productExtDto = consumerService.readFromTopic(json)

        assertEquals(ProductExtDTO(10, "NAME_10", 100), productExtDto)
    }
}