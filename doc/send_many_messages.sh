#!/bin/bash

# for test send many messages to queue `product_ext_dto_topic`  and view in console received messages
# run from ./doc

# example:
# {"n":1,"name":"NAME_1","groupDtoN":1}
# {"n":1,"name":"NAME_1","groupDtoN":1}
# {"n":1,"name":"NAME_1","groupDtoN":1}


# one way 'for'
#for i in $(seq 1 $max)

# other way 'for'
cat /dev/null > ./product_many.json

# generate json for sending to queue
max=120
for ((i=1; i < max; i++))
do
  echo "{\"n\":$i,\"name\":\"NAME_$i\",\"groupDtoN\":$i}" >> ./product_many.json
done

# send json to queue
./run-producer.sh product_ext_dto_topic < ./product_many.json
