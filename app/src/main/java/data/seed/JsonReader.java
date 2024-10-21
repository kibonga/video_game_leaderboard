package data.seed;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonReader<T> {

  private static final Logger logger = LoggerFactory.getLogger(JsonReader.class);
  private final Class<T> type;

  public JsonReader(Class<T> type) {
    this.type = type;
  }

  public List<T> read(String path) {
    final List<T> data = new ArrayList<>();
    final var gson = new Gson();
    try (final Reader reader = new FileReader(path)) {
      // final var token = new TypeToken<List<T>>(){}.getType();
      final var token = TypeToken.getParameterized(List.class, this.type).getType();
      data.addAll(gson.fromJson(reader, token));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return data;
  }

}
