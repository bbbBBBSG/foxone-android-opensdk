package com.fox.one.opensdk.demo

import android.app.Application
import com.fox.one.base.FoxRuntime
import com.fox.one.opensdk.FoxOneOpenSDK

/**
 * class description here
 * @author xiaoming1109@gmail.com
 * @version 1.0.0
 * @since 2018-09-04
 */
class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        FoxOneOpenSDK.init(this, "123456")
    }
}