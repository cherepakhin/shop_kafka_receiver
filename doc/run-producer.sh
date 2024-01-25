#!/bin/bash

#$1 - topic for send
/opt/kafka/bin/kafka-console-producer.sh --broker-list 192.168.1.20:9092 --topic $1