docker-compose exec kafka bash -c "
kafka-console-producer \
--bootstrap-server kafka:9092 \
--topic players \
--property 'parse.key=true' \
--property 'key.separator=|' < proto/players.bin"

docker-compose exec kafka bash -c "
kafka-console-producer \
--bootstrap-server kafka:9092 \
--topic products \
--property 'parse.key=true' \
--property 'key.separator=|' < proto/products.bin"

docker-compose exec kafka bash -c "
kafka-console-producer \
--bootstrap-server kafka:9092 \
--topic score-events < proto/score-events.bin"