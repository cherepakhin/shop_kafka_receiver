#!/bin/bash

# for test send many messages to queue `product_ext_dto_topic`  and view in console received messages
# run from ./doc
max=2
# one way 'for'
#for i in $(seq 1 $max)
# other way 'for'
echo > ./product_many.json
for ((i=1; i < max; i++))
do
#    ./run-producer.sh product_ext_dto_topic < ./product.json
  echo "{\"n\":$i,\"name\":\"NAME_$i\",\"groupDtoN\":$i}" >> ./product_many.json
#  ./run-producer.sh product_ext_dto_topic < ./product_many.json
done
./run-producer.sh product_ext_dto_topic < ./product_many.json
