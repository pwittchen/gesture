package com.github.pwittchen.gesture.library;

import android.view.MotionEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GestureTest {

  private Gesture gesture;
  private GestureEvent gestureEvent;
  @Mock private MotionEvent motionEventDown;
  @Mock private MotionEvent motionEventMove;
  @Mock private MotionEvent motionEventUp;

  @Before public void setUp() {
    this.gesture = new Gesture();
    this.gesture.addListener(new GestureListener() {
      @Override public void onPress(MotionEvent motionEvent) {
        gestureEvent = GestureEvent.ON_PRESS;
      }

      @Override public void onTap(MotionEvent motionEvent) {
        gestureEvent = GestureEvent.ON_TAP;
      }

      @Override public void onDrag(MotionEvent motionEvent) {
        gestureEvent = GestureEvent.ON_DRAG;
      }

      @Override public void onMove(MotionEvent motionEvent) {
        gestureEvent = GestureEvent.ON_MOVE;
      }

      @Override public void onRelease(MotionEvent motionEvent) {
        gestureEvent = GestureEvent.ON_RELEASE;
      }

      @Override public void onLongPress(MotionEvent motionEvent) {
        gestureEvent = GestureEvent.ON_LONG_PRESS;
      }

      @Override public void onMultiTap(MotionEvent motionEvent, int clicks) {
        gestureEvent = GestureEvent.ON_MULTI_TAP.withClicks(clicks);
      }
    });
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionWhenListenerIsNull() {
    gesture.addListener(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionWhenMotionEventIsNull() {
    gesture.dispatchTouchEvent(null);
  }

  //TODO: implement tests for different methods of the listener
}
