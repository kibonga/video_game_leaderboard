echo "Waiting for Kafka to come online..."

cub kafka-ready -b kafka:9092 1 20

# Create game events topic
kafka-topics \
--bootstrap-server kafka:9092 \
--topic score-events \
--replication-factor 1 \
--partitions 4 \
--create

# Create players topic
kafka-topics \
--bootstrap-server kafka:9092 \
--topic players \
--replication-factor 1 \
--partitions 4 \
--create

# Create products topic
kafka-topics \
--bootstrap-server kafka:9092 \
--topic products \
--replication-factor 1 \
--partitions 4 \
--create

# Create high-scores topic
kafka-topics \
--bootstrap-server kafka:9092 \
--topic high-scores \
--replication-factor 1 \
--partitions 4 \
--create

sleep infinity