package com.template.module_common.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.template.module_common.utils.HToast
import com.template.module_common.utils.Utils

open class BaseApplication :Application() {


    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        Utils.init(this)
        HToast.init(this)

        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this);
    }

    companion object{
        private lateinit var baseApplication:BaseApplication

        fun getContext(): Context {
            return baseApplication
        }
    }
}