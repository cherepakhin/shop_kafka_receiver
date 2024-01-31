package ru.perm.v.shopkotlin.kafka_consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShopKafkaConsumerApp {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ShopKafkaConsumerApp>(*args)
        }
    }
}
