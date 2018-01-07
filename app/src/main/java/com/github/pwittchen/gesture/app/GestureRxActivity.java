package com.github.pwittchen.gesture.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import com.github.pwittchen.gesture.library.Gesture;
import com.github.pwittchen.gesture.library.GestureEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Locale;

public class GestureRxActivity extends AppCompatActivity {
  private Gesture gesture;
  private TextView textView;
  private Disposable subscription;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    textView = (TextView) findViewById(R.id.textView);
    gesture = new Gesture();
    subscription = gesture.observe()
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(event -> {
          final String msg = event.toString();
          if (event.equals(GestureEvent.ON_MULTI_TAP)) {
            textView.setText(
                msg.concat(String.format(Locale.getDefault(), " [%d]", event.getClicks())));
          } else {
            textView.setText(msg);
          }
        });
  }

  @Override public boolean dispatchTouchEvent(MotionEvent event) {
    gesture.dispatchTouchEvent(event);
    return super.dispatchTouchEvent(event);
  }

  @Override protected void onPause() {
    super.onPause();
    safelyDispose(subscription);
  }

  private void safelyDispose(Disposable disposable) {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
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
