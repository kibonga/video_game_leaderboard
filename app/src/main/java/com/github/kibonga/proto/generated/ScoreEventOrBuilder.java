// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Video-game-leaderboard.proto

package com.github.kibonga.proto.generated;

public interface ScoreEventOrBuilder extends
    // @@protoc_insertion_point(interface_extends:leaderboard.ScoreEvent)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 playerId = 1;</code>
   * @return The playerId.
   */
  long getPlayerId();

  /**
   * <code>int64 productId = 2;</code>
   * @return The productId.
   */
  long getProductId();

  /**
   * <code>double score = 3;</code>
   * @return The score.
   */
  double getScore();
}
