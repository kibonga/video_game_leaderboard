package com.github.kibonga.common.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Player {
  private Long id;
  private String name;
}
