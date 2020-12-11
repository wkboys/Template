package com.template.service

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyServiceObserver : LifecycleObserver {

    var TAG: String =this.javaClass.name

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startGetLocation(){
        Log.e("www","start MyServiceObserver")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopGetLocation(){
        Log.e("www","stop MyServiceObserver")
    }
}