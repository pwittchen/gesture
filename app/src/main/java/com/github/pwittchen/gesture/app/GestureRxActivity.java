package com.github.pwittchen.gesture.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import com.github.pwittchen.gesture.library.Gesture;
import com.github.pwittchen.gesture.library.GestureEvent;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GestureRxActivity extends AppCompatActivity {
  private Gesture gesture;
  private TextView textView;
  private Subscription subscription;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    textView = (TextView) findViewById(R.id.textView);
    gesture = new Gesture();
    subscription = gesture.observe()
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<GestureEvent>() {
          @Override public void call(GestureEvent event) {
            final String msg = event.toString();
            if (event.equals(GestureEvent.ON_MULTI_TAP)) {
              textView.setText(msg.concat(String.format(" [%d]", event.getClicks())));
            } else {
              textView.setText(msg);
            }
          }
        });
  }

  @Override public boolean dispatchTouchEvent(MotionEvent event) {
    gesture.dispatchTouchEvent(event);
    return super.dispatchTouchEvent(event);
  }

  @Override protected void onPause() {
    super.onPause();
    safelyUnsubscribe(subscription);
  }

  private void safelyUnsubscribe(Subscription subscription) {
    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.listener:
        onBackPressed();
        break;
      case R.id.rx:
        break;
    }
    return true;
  }
}
