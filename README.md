gesture [![Build Status](https://travis-ci.org/pwittchen/gesture.svg?branch=master)](https://travis-ci.org/pwittchen/gesture)
=======

detects gestures on Android with listener and RxJava Observable. 

This project is a fork of [better-gesture-detector](https://github.com/Polidea/better-gesture-detector) by [Polidea](https://github.com/Polidea).

WIP - Documentation and examples will be provided later.

Contents
--------
- [Usage](#usage)
  - [Imperative way - Listener](#imperative-way---listener)
  - [Reactive way - RxJava](#reactive-way---rxjava)
- [Examples](#examples)
- [Download](#download)
- [Tests](#tests)
- [Code style](#code-style)
- [Static code analysis](#static-code-analysis)
- [Credits](#credits)
- [License](#license)

Usage
-----

### Imperative way - Listener

**Step 1**: Create `Gesture` attribute in the `Activity`:

```java
private Gesture gesture;
```

**Step 2**: Initialize `Gesture` object and add `GestureListner`:

```java
gesture = new Gesture();

gesture.addListener(new GestureListener() {
  @Override public void onPress(MotionEvent motionEvent) {
    textView.setText("press");
  }

  @Override public void onTap(MotionEvent motionEvent) {
    textView.setText("tap");
  }

  @Override public void onDrag(MotionEvent motionEvent) {
    textView.setText("drag");
  }

  @Override public void onMove(MotionEvent motionEvent) {
    textView.setText("move");
  }

  @Override public void onRelease(MotionEvent motionEvent) {
    textView.setText("release");
  }

  @Override public void onLongPress(MotionEvent motionEvent) {
    textView.setText("longpress");
  }

  @Override public void onMultiTap(MotionEvent motionEvent, int clicks) {
    textView.setText("multitap [" + clicks + "]");
  }
});
```

**Step 3**: Override `dispatchTouchEvent(MotionEvent)` method:

```java
@Override public boolean dispatchTouchEvent(MotionEvent event) {
  gesture.dispatchTouchEvent(event);
  return super.dispatchTouchEvent(event);
}
```

### Reactive way - RxJava

**Step 1**: Create `Gesture` attribute and `Subscription` in the `Activity`:

```java
private Gesture gesture;
private Subscription subscription;
```

**Step 2**: Initialize `Gesture` object and subscribe RxJava `Observable`:

```java
gesture = new Gesture();

subscription = gesture.observe()
  .subscribeOn(Schedulers.computation())
  .observeOn(AndroidSchedulers.mainThread())
  .subscribe(new Action1<GestureEvent>() {
    @Override public void call(GestureEvent event) {
      textView.setText(event.toString());
    }
  });
```

**Step 3**: Override `dispatchTouchEvent(MotionEvent)` method:

```java
@Override public boolean dispatchTouchEvent(MotionEvent event) {
  gesture.dispatchTouchEvent(event);
  return super.dispatchTouchEvent(event);
}
```

**Step 4**: Don't forget to unsubscribe subscription when it's no longer needed:

```java
@Override protected void onPause() {
  super.onPause();
  if (subscription != null && !subscription.isUnsubscribed()) {
    subscription.unsubscribe();
  }
}
```

Examples
--------

Exemplary application is located in `app` directory of this repository.

Download
--------

TBD.

Tests
-----

TBD.

Code style
----------

Code style used in the project is called `SquareAndroid` from Java Code Styles repository by Square available at: https://github.com/square/java-code-styles.

Static code analysis
--------------------

Static code analysis runs Checkstyle, FindBugs, PMD and Lint. It can be executed with command:

 ```
 ./gradlew check
 ```

Reports from analysis are generated in `library/build/reports/` directory.

Credits
-------

Initial code base for this project is provided by [Polidea](https://github.com/Polidea).

License
-------

    Copyright 2012 Polidea
    Copyright 2016 Piotr Wittchen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
