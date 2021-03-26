package yzx.util.clutter

import android.os.Handler


/**
 * 简单计时器, 类似CountDownTimer
 * @param interval 两次回调的间隔时间
 * @param onTick 回调
 */
class SimpleTimer(val interval: Long, val onTick: (timer: SimpleTimer) -> Unit) {


    private val handler = Handler()

    @Volatile
    private var running = false

    private val run = object : Runnable {
        override fun run() {
            if (running) {
                onTick.invoke(this@SimpleTimer)
                if (running)
                    handler.postDelayed(this, interval)
            }
        }
    }


    /**
     * 开始不断回调
     * @param callbackImmediately 是否立即执行回调
     */
    fun start(callbackImmediately: Boolean = true) {
        if (running) return
        running = true
        handler.postDelayed(run, if (callbackImmediately) 0 else interval)
    }


    /**
     * 停止计时回调
     */
    fun stop() {
        running = false
        handler.removeCallbacks(run)
    }

}