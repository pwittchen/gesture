/*
 * Copyright 2012 Polidea
 * Copyright 2016 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pwittchen.gesture.library;

import android.view.MotionEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class) public class GestureTest {

  private Gesture gesture;
  private GestureEvent gestureEvent;
  @Mock private MotionEvent motionEventDown;
  @Mock private MotionEvent motionEventMove;
  @Mock private MotionEvent motionEventUp;

  @Before public void setUp() {
    gesture = new Gesture();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionWhenListenerIsNull() {
    gesture.addListener(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowAnExceptionWhenMotionEventIsNull() {
    gesture.dispatchTouchEvent(null);
  }

  @Test public void shouldAddLListener() {
    gesture.addListener(new GestureListener() {
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
}
