package yzx.util.clutter

import android.os.SystemClock
import android.view.View


/** 防止单个view在很短时间连续响应点击事件的最短间隔时间的默认值 */
var defaultViewClickListener2Interval = 300

/** 防止全局view在很短时间连续响应点击事件的最短间隔时间的默认值 */
var defaultViewClickListener3Interval = 300


/**
 * 防止单个view在很短时间连续响应点击事件
 */
fun View.setClickListener2(listener: View.OnClickListener, interval: Int = defaultViewClickListener2Interval) {
    this.setOnClickListener {
        val lastClickTime = this.getTag(R.id.clutter_view_click2) as? Long ?: -1
        val now = SystemClock.uptimeMillis()
        val intervalBetween = now - lastClickTime
        if (intervalBetween > interval) {
            listener.onClick(it)
            this.setTag(R.id.clutter_view_click2, now)
        }
    }
}


// 记录全局的点击事件时间
private var globalLastClickTime = -1L

/**
 * 防止全局view在很短时间连续响应点击事件
 * 例如当点击的view1后, 在指定时间内点击view2, view2是不会响应点击事件的
 */
fun View.setClickListener3(listener: View.OnClickListener, interval: Int = defaultViewClickListener3Interval) {
    this.setOnClickListener {
        val lastClickTime = globalLastClickTime
        val now = SystemClock.uptimeMillis()
        val intervalBetween = now - lastClickTime
        if (intervalBetween > interval) {
            listener.onClick(it)
            globalLastClickTime = now
        }
    }
}