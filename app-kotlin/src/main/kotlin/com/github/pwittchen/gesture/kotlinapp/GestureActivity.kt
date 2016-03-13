package com.github.pwittchen.gesture.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import com.github.pwittchen.gesture.library.Gesture
import com.github.pwittchen.gesture.library.GestureListener
import kotlinx.android.synthetic.main.main.textView

class GestureActivity : AppCompatActivity() {
  private var gesture: Gesture? = null
  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)

    gesture = Gesture()

    (gesture as Gesture).addListener(object : GestureListener {
      override fun onPress(motionEvent: MotionEvent) {
        textView.text = "press"
      }

      override fun onTap(motionEvent: MotionEvent) {
        textView.text = "tap"
      }

      override fun onDrag(motionEvent: MotionEvent) {
        textView.text = "drag"
      }

      override fun onMove(motionEvent: MotionEvent) {
        textView.text = "move"
      }

      override fun onRelease(motionEvent: MotionEvent) {
        textView.text = "release"
      }

      override fun onLongPress(motionEvent: MotionEvent) {
        textView.text = "longpress"
      }

      override fun onMultiTap(motionEvent: MotionEvent, clicks: Int) {
        textView.text = "multitap [$clicks]"
      }
    })
  }

  override fun dispatchTouchEvent(event: MotionEvent): Boolean {
    gesture!!.dispatchTouchEvent(event)
    return super.dispatchTouchEvent(event)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.listener -> {
      }
      R.id.rx -> {
        val intent = Intent(this, GestureRxActivity::class.java)
        startActivity(intent)
      }
    }
    return true
  }
}