package com.github.kibonga.common.proto;

import com.google.protobuf.GeneratedMessageV3;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProtoReader<T extends GeneratedMessageV3> {

  private final Class<T> type;

  public ProtoReader(Class<T> type) {
    this.type = type;
  }

  @SuppressWarnings("unchecked")
  public List<T> read(String path) {
    final var list = new ArrayList<T>();

    try(final FileInputStream fileInputStream = new FileInputStream(path)) {
      T message;
      final var parseDelimitedFrom = this.type.getMethod("parseDelimitedFrom", InputStream.class);

      while((message = (T) parseDelimitedFrom.invoke(null, fileInputStream)) != null) {
        list.add(message);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ReflectiveOperationException e) {
     throw new RuntimeException("Failed to invoke parseDelimitedFrom method");
    }

    return list;
  }

}
