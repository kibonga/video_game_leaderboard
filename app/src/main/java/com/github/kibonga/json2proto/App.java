package com.github.kibonga.json2proto;

import com.github.kibonga.common.json.JsonReader;
import com.github.kibonga.common.proto.ProtoWriter;
import com.google.protobuf.GeneratedMessageV3;
import com.github.kibonga.common.models.Player;
import com.github.kibonga.common.models.Product;
import com.github.kibonga.common.models.ScoreEvent;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final String jsonDir = "/data/json/";
  private static final String binDir = "/data/bin/";
  private static final String scoreEventsJson = "score-events.json";
  private static final String playersJson = "players.json";
  private static final String productsJson = "products.json";
  private static final String scoreEventsBin = "score-events.bin";
  private static final String playersBin = "players.bin";
  private static final String productsBin = "products.bin";


  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    final var scoreEventsJsonReader = new JsonReader<>(ScoreEvent.class);
    final var playersJsonReader = new JsonReader<>(Player.class);
    final var productsJsonReader = new JsonReader<>(Product.class);

    final var rootDir = System.getProperty("user.dir");
    final var jsonPath = rootDir + jsonDir;
    final var binPath = rootDir + binDir;

    final var playersJsonPath = jsonPath + playersJson;
    final var productsJsonPath = jsonPath + productsJson;
    final var scoreEventsJsonPath = jsonPath + scoreEventsJson;

    final var playersBinPath = binPath + playersBin;
    final var productsBinPath = binPath + productsBin;
    final var scoreEventsBinPath = binPath + scoreEventsBin;

    logger.info("Root dir={}", rootDir);
    logger.info("Json path={}", jsonPath);
    logger.info("Proto path={}", binPath);

    logger.info("Players json path={}", playersJsonPath);
    logger.info("Products json path={}", productsJsonPath);
    logger.info("Score events json path={}", scoreEventsJsonPath);

    logger.info("Players bin path={}", playersBinPath);
    logger.info("Products bin path={}", productsBinPath);
    logger.info("Score events bin path={}", scoreEventsBinPath);

    logger.info("Reading from json...");
    final var playersJsonData = playersJsonReader.read(playersJsonPath);
    final var productsJsonData = productsJsonReader.read(productsJsonPath);
    final var scoreEventsJsonData = scoreEventsJsonReader.read(scoreEventsJsonPath);
    logger.info("Successfully read from json.");

    logger.info("Converting json to proto...");
    final var playersProtoData = convertToProto(playersJsonData, playerFunction);
    final var productsProtoData = convertToProto(productsJsonData, productFunction);
    final var scoreEventsProtoData = convertToProto(scoreEventsJsonData, scoreEventFunction);
    logger.info("Successfully converted json to proto.");

    final var scoreEventsProtoWriter =
        new ProtoWriter<>(com.github.kibonga.proto.generated.ScoreEvent.class);
    final var playersProtoWriter =
        new ProtoWriter<>(com.github.kibonga.proto.generated.Player.class);
    final var productsProtoWriter =
        new ProtoWriter<>(com.github.kibonga.proto.generated.Product.class);

    logger.info("Writing proto to bin...");
    playersProtoWriter.write(playersBinPath, playersProtoData);
    productsProtoWriter.write(productsBinPath, productsProtoData);
    scoreEventsProtoWriter.write(scoreEventsBinPath, scoreEventsProtoData);
    logger.info("Successfully wrote proto to bin");
  }

  private static <T, R extends GeneratedMessageV3> List<R> convertToProto(List<T> types,
      Function<T, R> toProtoDelegate) {
    return types.stream().map(toProtoDelegate).collect(Collectors.toList());
  }

  private static final Function<ScoreEvent, com.github.kibonga.proto.generated.ScoreEvent>
      scoreEventFunction = scoreEvent -> com.github.kibonga.proto.generated.ScoreEvent
      .newBuilder()
      .setScore(scoreEvent.getScore())
      .setProductId(scoreEvent.getProductId())
      .setPlayerId(scoreEvent.getPlayerId())
      .build();

  private static final Function<Player, com.github.kibonga.proto.generated.Player> playerFunction =
      player ->
          com.github.kibonga.proto.generated.Player
              .newBuilder()
              .setId(player.getId())
              .setName(player.getName())
              .build();

  private static final Function<Product, com.github.kibonga.proto.generated.Product>
      productFunction = product ->
      com.github.kibonga.proto.generated.Product
          .newBuilder()
          .setId(product.getId())
          .setName(product.getName())
          .build();

}
