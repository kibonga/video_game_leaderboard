package data.seed;

import com.google.protobuf.GeneratedMessageV3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoWriter<T extends GeneratedMessageV3> {
  private final static Logger logger = LoggerFactory.getLogger(ProtoWriter.class);

  private final Class<T> type;

  public ProtoWriter(Class<T> type) {
    this.type = type;
  }

  public void write(String path, List<T> list) {
    try(final FileOutputStream fileOutputStream = new FileOutputStream(path)) {
      for (final var protoObj : list) {
        protoObj.writeDelimitedTo(fileOutputStream);
      }
    } catch (IOException e) {
      logger.error("An error occurred writing proto messages to bin file. Ex={}", e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public void write(String path, T obj) {
    try(final FileOutputStream fileOutputStream = new FileOutputStream(path)) {
      obj.writeDelimitedTo(fileOutputStream);
    } catch (IOException e) {
      logger.error("An error occurred writing proto messages to bin file. Ex={}", e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
