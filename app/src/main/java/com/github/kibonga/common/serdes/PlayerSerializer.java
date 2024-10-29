package com.github.kibonga.common.serdes;

import org.apache.kafka.common.serialization.Serializer;
import com.github.kibonga.proto.generated.Player;

public class PlayerSerializer implements Serializer<Player> {

  @Override
  public byte[] serialize(String topic, Player data) {
   return data.toByteArray();
  }

  @Override
  public void close() {}
}
