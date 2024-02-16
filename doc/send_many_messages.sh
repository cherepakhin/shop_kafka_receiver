#!/bin/bash

# for test send many messages to queue `product_ext_dto_topic`  and view in console received messages
# run from ./doc

# example:
# {"n":1,"name":"NAME_1","groupDtoN":1}
# {"n":1,"name":"NAME_1","groupDtoN":1}
# {"n":1,"name":"NAME_1","groupDtoN":1}


cat /dev/null > ./product_list.json

# count number of messages
max=120

# generate messages
for ((i=1; i < max; i++))
do
  echo "{\"n\":$i,\"name\":\"NAME_$i\",\"groupDtoN\":$i}" >> ./product_list.json
done

# send json to queue
./run-producer.sh product_ext_dto_topic < ./product_list.json
