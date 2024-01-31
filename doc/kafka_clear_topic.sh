# change timeout to clear messages
# Настроить политики хранения данных, чтобы контролировать,
# сколько и как долго данные будут храниться,
# можно с помощью конфигураций retention.bytes и retention.ms
/opt/kafka/bin/kafka-configs.sh --zookeeper 192.168.1.20:2181 --entity-type topics --alter --entity-name product_ext_dto_topic --add-config retention.ms=1000

# delete topic
#/opt/kafka/bin/kafka-topics.sh --zookeeper 192.168.1.20:2181 --delete --topic product_ext_dto_topic

/opt/kafka/bin/kafka-configs.sh --bootstrap-server 192.168.1.20:2181 --entity-type topics --alter --entity-name product_ext_dto_topic --add-config retention.ms=1000
