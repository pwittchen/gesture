package com.github.pwittchen.gesture.library;

public enum GestureEvent {
  ON_PRESS("press"),
  ON_TAP("tap"),
  ON_DRAG("drag"),
  ON_MOVE("move"),
  ON_RELEASE("release"),
  ON_LONG_PRESS("long press"),
  ON_MULTI_TAP("multi tap");

  private final String name;
  private int taps = 1;

  GestureEvent(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getTaps() {
    return taps;
  }

  public GestureEvent withTaps(int taps) {
    this.taps = taps;
    return this;
  }
}
