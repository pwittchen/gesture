package com.github.pwittchen.gesture.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import com.github.pwittchen.gesture.library.Gesture;
import com.github.pwittchen.gesture.library.GestureListener;

public class GestureActivity extends AppCompatActivity {
  private Gesture gesture;
  private TextView textView;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    textView = (TextView) findViewById(R.id.textView);
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
  }

  @Override public boolean dispatchTouchEvent(MotionEvent event) {
    gesture.dispatchTouchEvent(event);
    return super.dispatchTouchEvent(event);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.listener:
        break;
      case R.id.rx:
        final Intent intent = new Intent(this, GestureRxActivity.class);
        startActivity(intent);
        break;
    }
    return true;
  }
}