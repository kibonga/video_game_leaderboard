package com.github.kibonga.producer;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class Producer<K, V> {

  private static final Logger logger = LoggerFactory.getLogger(Producer.class);
  private static final String bootstrapServer = "localhost:29092";

  private final KafkaProducerBuilder kafkaProducerBuilder;

  public Producer(Class<K> keySerializer, Class<V> valueSerializer) {
    this.kafkaProducerBuilder = new KafkaProducerBuilder()
        .bootstrapServer(bootstrapServer)
        .keySerializer(keySerializer)
        .valueSerializer(valueSerializer);
  }

}
