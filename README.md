## Простой проект на Kotlin и Spring Boot для приема сообщений из очереди Kafka

### Оглавление:
[Цель](#target)<br/>
[Параметры запуска сервера Kafka](#parameters)<br/>
[Проверка работы с Kafka из shell](#work_in_shell)<br/>
[Запуск проекта](#run_receiver)<br/>
[Ручная отправка в очередь из консоли продюсера](#manual_send)<br/>
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
### Проверка работы с Kafka из shell

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

<a id="run_receiver"></a>
### Запуск проекта

````shell
~$ ./gradlew bootRun
[ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : test_id: partitions assigned: [json_topic-0]
[ntainer#1-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : test_id: partitions assigned: [test_topic_text-0]
````

Запущено слушатели для очередей "json_topic", "test_topic_text" .

<a id="manual_send"></a>
### Ручная отправка в очередь из консоли продюсера 
[doc/run-producer.sh](https://github.com/cherepakhin/shop_kafka_receiver/blob/dev/doc/run-producer.sh)

````shell
$ ./doc/run-producer.sh test_topic_text
>MESSAGE_TEXT
````

Тестовый сервис приема сообщений из топика __"test_topic_text"__:
[KafkaConsumerTestTopicTextService.kt](https://github.com/cherepakhin/shop_kafka_receiver/blob/dev/src/main/kotlin/ru/perm/v/shopkotlin/kafka_receiver/KafkaConsumerTestTopicTextService.kt)

Тестовый сервис приема сообщений из топика __"json_topic"__:
[KafkaConsumerJsonTopicService.kt](https://github.com/cherepakhin/shop_kafka_receiver/blob/dev/src/main/kotlin/ru/perm/v/shopkotlin/kafka_receiver/KafkaConsumerJsonTopicService.kt)

Логирование принятого сообщения в программе из топика "test_topic_text":

````shell
INFO 10436 --- [ntainer#1-0-C-1] .v.s.k.KafkaConsumerTestTopicTextService : MESSAGE_TEXT
````

Логирование принятого сообщения в программе из топика "json_topic":

````shell
INFO 4849 --- [ntainer#0-0-C-1] r.p.v.s.k.KafkaConsumerJsonTopicService  : MESSAGE_TO_JSON_TOPIC
````

<a id="nexus"></a>
### Deploy to NEXUS repository

Для deploy выполнить:

````shell
./gradlew publish
````

Путь к репозиторию установлен в build.gradle.kts:

````shell
url = uri("http://v.perm.ru:8082/repository/ru.perm.v/")
````

Для установки переменных доступа к Nexus repository выполнить в shell:

````shell
$ export NEXUS_CRED_USR=admin
$ export NEXUS_CRED_PSW=pass
````

<a id="links"></a>
### Ссылки

- [Основной проект](https://github.com/cherepakhin/shop_kotlin)
- [https://www.baeldung.com/rest-template](https://www.baeldung.com/rest-template)
- [Конфигурирование, запуск и работа с Kafka](http://v.perm.ru/main/index.php/homepage/66-konfigurirovanie-zapusk-i-rabota-s-kafka)
- [Apache Kafka with Kotlin](https://www.baeldung.com/kotlin/apache-kafka)