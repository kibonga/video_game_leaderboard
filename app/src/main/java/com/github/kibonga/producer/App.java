package com.github.kibonga.producer;

import com.github.kibonga.common.serdes.PlayerDeserializer;
import com.github.kibonga.common.serdes.PlayerSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    logger.info("Starting kafka producer...");

    final var playerProducerBuilder = new Producer<>(ByteArraySerializer.class, PlayerSerializer.class).getKafkaProducerBuilder();

    try(final var playerProducer = playerProducerBuilder.build()) {

      playerProducer.flush();
      playerProducer.close();
    }catch (Exception e) {
      logger.error("Player Producer Exception. Error={}", e.getMessage());
    }


    logger.info("Closing kafka producer...");
  }
}
