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

public interface GestureListener {

  void onPress(MotionEvent motionEvent);

  void onTap(MotionEvent motionEvent);

  void onDrag(MotionEvent motionEvent);

  void onMove(MotionEvent motionEvent);

  void onRelease(MotionEvent motionEvent);

  void onLongPress(MotionEvent motionEvent);

  void onMultiTap(MotionEvent motionEvent, int clicks);
}
