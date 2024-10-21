package data.seed;

import com.google.protobuf.GeneratedMessageV3;
import common.data.Player;
import common.data.Product;
import common.data.ScoreEvent;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final String baseJsonPath = "data/json/";
  private static final String baseProtoPath = "data/proto/";
  private static final String scoreEventsJson = "score-events.json";
  private static final String playersJson = "players.json";
  private static final String productsJson = "products.json";
  private static final String scoreEventsBinary = "score-events.bin";
  private static final String playersBinary = "players.bin";
  private static final String productsBinary = "products.bin";


  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    final var scoreEventsJsonReader = new JsonReader<>(ScoreEvent.class);
    final var playersJsonReader = new JsonReader<>(Player.class);
    final var productsJsonReader = new JsonReader<>(Product.class);

    logger.info("Reading from json...");
    final var scoreEventsJsonData = scoreEventsJsonReader.read(baseJsonPath + scoreEventsJson);
    final var playersJsonData = playersJsonReader.read(baseJsonPath + playersJson);
    final var productsJsonData = productsJsonReader.read(baseJsonPath + productsJson);
    logger.info("Successfully read from json.");

    logger.info("Converting json to proto...");
    final var scoreEventsProtoData = (List<proto.generated.ScoreEvent>) convertToProto(scoreEventsJsonData, scoreEventFunction);
    final var playersProtoData = convertToProto(playersJsonData, playerFunction);
    final var productsProtoData = convertToProto(productsJsonData, productFunction);
    logger.info("Successfully converted json to proto.");

    final var scoreEventsProtoWriter = new ProtoWriter<>(proto.generated.ScoreEvent.class);
    final var playersProtoWriter = new ProtoWriter<>(proto.generated.Player.class);
    final var productsProtoWriter = new ProtoWriter<>(proto.generated.Product.class);

    logger.info("Writing proto to bin...");
    scoreEventsProtoWriter.write(baseProtoPath + scoreEventsBinary, scoreEventsProtoData);
    playersProtoWriter.write(baseProtoPath + playersBinary, playersProtoData);
    productsProtoWriter.write(baseProtoPath + productsBinary, productsProtoData);
    logger.info("Successfully wrote proto to bin");
  }

  private static <T, R extends GeneratedMessageV3> List<R> convertToProto(List<T> types, Function<T, R> tFunction) {
    return types.stream().map(tFunction).collect(Collectors.toList());
  }

  private static final Function<ScoreEvent, proto.generated.ScoreEvent> scoreEventFunction = scoreEvent -> proto.generated.ScoreEvent
      .newBuilder()
      .setScore(scoreEvent.getScore())
      .setProductId(scoreEvent.getProductId())
      .setPlayerId(scoreEvent.getPlayerId())
      .build();

  private static final Function<Player, proto.generated.Player> playerFunction = player ->
      proto.generated.Player
          .newBuilder()
          .setId(player.getId())
          .setName(player.getName())
          .build();

  private static final Function<Product, proto.generated.Product> productFunction = product ->
      proto.generated.Product
          .newBuilder()
          .setId(product.getId())
          .setName(product.getName())
          .build();

}
