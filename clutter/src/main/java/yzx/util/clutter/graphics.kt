package yzx.util.clutter

import android.graphics.BitmapFactory
import android.graphics.Color


fun Int.replaceAlpha(alpha: Int): Int {
    return Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}


fun getBitmapSize(filePath: String): IntArray {
    val option = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    BitmapFactory.decodeFile(filePath, option)
    return IntArray(2).apply {
        set(0, option.outWidth)
        set(1, option.outHeight)
    }
}