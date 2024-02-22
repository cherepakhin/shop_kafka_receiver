#!/bin/bash
# $1 - topic from which to consume
#~/tools/kafkin/kafka-console-consumer.sh --bootstrap-server 192.168.1.20:9092 --topic product_ext_dto_topic
# ./run_consumer.sh product_ext_dto_topic

# Example:
# for test send message to topic:
# run from  directory ~/prog/kotlin/shop/kafka/shop_kafka_producer/doc/send_file command:
#   cat ./list_product| /opt/kafka/bin/kafka-console-producer.sh --broker-list 192.168.1.20:9092 --topic product_ext_dto_topic
# $1 - topic from which to consume(product_ext_dto_topic)
/opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server 192.168.1.20:9092 --topic $1