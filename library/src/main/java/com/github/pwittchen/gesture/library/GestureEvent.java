package com.github.pwittchen.gesture.library;

public enum GestureEvent {
  ON_PRESS,
  ON_TAP,
  ON_DRAG,
  ON_MOVE,
  ON_RELEASE,
  ON_LONG_PRESS,
  ON_MULTI_TAP;

  private int clicks = 1;

  public int getClicks() {
    return clicks;
  }

  public GestureEvent withClicks(int clicks) {
    this.clicks = clicks;
    return this;
  }
}
