package common.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScoreEvent {
  private Long playerId;
  private Long productId;
  private Double score;
}
