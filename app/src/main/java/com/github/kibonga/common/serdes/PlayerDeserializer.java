package com.github.kibonga.common.serdes;

import com.github.kibonga.proto.generated.Player;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerDeserializer implements Deserializer<Player> {
  private static final Logger logger = LoggerFactory.getLogger(PlayerDeserializer.class);
  @Override
  public Player deserialize(String topic, byte[] data) {
    try {
      if(data == null) {
        logger.warn("Received [null] bytes while deserializing player...");
      }
      return Player.parseFrom(data);
    } catch (Exception e) {
      throw new SerializationException("Error deserializing player bytes to...Error=" + e.getMessage());
    }
  }

  @Override
  public void close() {}
}
