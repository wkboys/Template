package com.template.module_common

import android.app.Application
import android.content.Context
import com.template.module_common.utils.HToast
import com.template.module_common.utils.Utils

/**
 * @date 2020/5/9
 * @author zs
 */
open class BaseApp :Application() {


    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        Utils.init(this)
        HToast.init(this)
    }

    companion object{
        lateinit var baseApplication:BaseApp

        fun getContext(): Context {
            return baseApplication
        }
    }
}