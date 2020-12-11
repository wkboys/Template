package com.template.service

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ApplicationObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.e("www","ApplicationObserver onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.e("www","ApplicationObserver onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.e("www","ApplicationObserver onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Log.e("www","ApplicationObserver onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Log.e("www","ApplicationObserver onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory(){
        Log.e("www","ApplicationObserver onDestory");
    }

}