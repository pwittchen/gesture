package com.github.pwittchen.gesture.library;

import org.junit.Before;
import org.junit.Test;

public class GestureTest {

  private Gesture gesture;

  @Before public void setUp() {
    this.gesture = new Gesture();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionWhenListenerIsNull() {
    gesture.addListener(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionWhenMotionEventIsNull() {
    gesture.dispatchTouchEvent(null);
  }
}
