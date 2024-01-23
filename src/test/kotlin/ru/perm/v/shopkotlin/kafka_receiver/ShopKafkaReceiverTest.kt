package ru.perm.v.shopkotlin.kafka_receiver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class ShopKafkaReceiverTest {

  @Test
  fun `simple demo test`() {
    assertEquals(1, 1)
  }
}
