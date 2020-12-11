package com.template.wk

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.template.module_common.BaseApp
import com.template.module_common.base.BaseApplication
import com.template.service.ApplicationObserver

class myApplication : BaseApp(){
    override fun onCreate() {
        super.onCreate()
        var  app:ApplicationObserver= ApplicationObserver()
        ProcessLifecycleOwner.get().lifecycle.addObserver(app)

    }
}