package ru.perm.v.shopkotlin.kafka_receiver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShopKafkaReceiverApp {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<ShopKafkaReceiverApp>(*args)
    }
  }
}
