package com.github.kibonga.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

public class KafkaProducerBuilder {

  private final Properties properties = new Properties();

  public KafkaProducerBuilder bootstrapServer(String server) {
    this.properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
    return this;
  }

  public KafkaProducerBuilder keySerializer(Class<?> serializer) {
    this.properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializer.getName());
    return this;
  }

  public KafkaProducerBuilder valueSerializer(Class<?> serializer) {
    this.properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer.getName());
    return this;
  }

  public <K, V> KafkaProducer<K, V> build() {
    return new KafkaProducer<>(this.properties);
  }
}
