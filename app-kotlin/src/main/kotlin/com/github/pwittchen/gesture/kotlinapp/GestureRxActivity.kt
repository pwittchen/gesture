package com.github.pwittchen.gesture.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import com.github.pwittchen.gesture.library.Gesture
import com.github.pwittchen.gesture.library.GestureEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main.*

class GestureRxActivity : AppCompatActivity() {
  private var gesture: Gesture? = null
  private var subscription: Disposable? = null

  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
    gesture = Gesture()

    subscription = (gesture as Gesture).observe()
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { event ->
          val msg = event.toString()
          if (event == GestureEvent.ON_MULTI_TAP) {
            textView.text = msg + String.format(" [%d]", event.clicks)
          } else {
            textView.text = msg
          }
        }
  }

  override fun dispatchTouchEvent(event: MotionEvent): Boolean {
    (gesture as Gesture).dispatchTouchEvent(event)
    return super.dispatchTouchEvent(event)
  }

  override fun onPause() {
    super.onPause()
    safelyDispose(subscription)
  }

  private fun safelyDispose(disposable: Disposable?) {
    if (disposable != null && !disposable.isDisposed) {
      disposable.dispose()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.listener -> onBackPressed()
      R.id.rx -> {
      }
    }
    return true
  }
}
