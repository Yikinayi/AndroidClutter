package yzx.util.clutter

import android.app.Application
import java.lang.Exception


/**
 * 使用反射获取application对象
 */
val application: Application by lazy {
    fun getByClassName(className: String, methodName: String): Application? {
        return try {
            @SuppressWarnings("PrivateAPI,DisCouragedPrivateAPI")
            val method = Class.forName(className).getDeclaredMethod(methodName)
            method.isAccessible = true
            method.invoke(null) as? Application
        } catch (ignore: Exception) {
            null
        }
    }

    val class1 = "android.app.ActivityThread"
    val method1 = "currentApplication"

    val class2 = "android.app.ActivityThread"
    val method2 = "getInitialApplication"

    getByClassName(class1, method1) ?: getByClassName(class2, method2) ?: error("获取application失败")
}