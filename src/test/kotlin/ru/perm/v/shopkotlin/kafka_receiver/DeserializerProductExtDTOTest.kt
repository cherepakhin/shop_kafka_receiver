package ru.perm.v.shopkotlin.kafka_receiver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.kafka.support.serializer.JsonDeserializer
import ru.perm.v.shopkotlin.extdto.ProductExtDTO

class DeserializerProductExtDTOTest {
    @Test
    fun fromJson() {
        val deserializer = JsonDeserializer(ProductExtDTO::class.java)
        val json = "{\"n\":10,\"name\":\"NAME_10\",\"groupDtoN\":100}"

        val productExtDto = deserializer.deserialize("", json.toByteArray())

        assertEquals(ProductExtDTO(10, "NAME_10", 100), productExtDto)
    }
}
