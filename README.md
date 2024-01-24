## Простой проект на Kotlin и Spring Boot для приема сообщений из очереди Kafka

### Оглавление:
[Цель](#target)<br/>
[Параметры запуска сервера Kafka](#parameters)<br/>
[Работа из shell](#work_in_shell)<br/>
[Ссылки](#links)<br/>

<a id="target"></a>
### Цель

Cоздать небольшое приложение на <b>Kotlin</b> с использованием <b>Spring Boot</b> для работы с <b>Kafka</b>.
Основной проект [https://github.com/cherepakhin/shop_kotlin](https://github.com/cherepakhin/shop_kotlin).
Программа будет принимать описания товаров из очереди Kafka и обновлять описания товаров через REST внешнего проекта shop_kotlin, используя [Spring RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html).

<a id="parameters"></a>
### Параметры запуска <ins>СЕРВЕРА</ins> Kafka

[Параметры сервера Kafka server.properties](https://github.com/cherepakhin/shop_kafka_receiver/blob/dev/doc/server.properties)

<a id="work_in_shell"></a>
### Работа из shell

Отправка сообщения:

````shell
~$ ~/tools/kafka/bin/kafka-console-producer.sh --bootstrap-server 192.168.1.20:9092 --topic samples
>MES
>MES1
>
````

Прием сообщения:

````shell
~$ ~/tools/kafka/bin/kafka-console-consumer.sh --bootstrap-server 192.168.1.20:9092 --topic samples
MES
MES1
````

Тестовый сервис приема сообщений из топика __"test_topic"__:
[KafkaConsumerTestTopicTextService.kt](https://github.com/cherepakhin/shop_kafka_receiver/blob/dev/src/main/kotlin/ru/perm/v/shopkotlin/kafka_receiver/KafkaConsumerTestTopicTextService.kt)

<a id="links"></a>
### Ссылки

- [Основной проект](https://github.com/cherepakhin/shop_kotlin)
- [https://www.baeldung.com/rest-template](https://www.baeldung.com/rest-template)
- [Конфигурирование, запуск и работа с Kafka](http://v.perm.ru/main/index.php/homepage/66-konfigurirovanie-zapusk-i-rabota-s-kafka)
- [Apache Kafka with Kotlin](https://www.baeldung.com/kotlin/apache-kafka)