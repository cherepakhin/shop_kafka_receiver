package ru.perm.v.shopkotlin.kafka_receiver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShopKafkaReceiver

fun main(args: Array<String>) {
  runApplication<ShopKafkaReceiver>(*args)
}
