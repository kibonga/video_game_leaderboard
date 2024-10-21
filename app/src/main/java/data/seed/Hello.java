package data.seed;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Hello {
  private int id;
  private String name;
  private String description;
}
