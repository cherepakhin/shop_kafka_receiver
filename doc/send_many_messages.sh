# run from ./doc
max=10
# one way 'for'
#for i in $(seq 1 $max)
# other way 'for'
for ((i=1; i < max; i++))
do
    ./run-producer.sh product_ext_dto_topic < ./product.json
done