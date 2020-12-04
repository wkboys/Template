package com.template.listener

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationListener(context: Context,onLocationListener: OnLocationChangeListener)  :LifecycleObserver{

    init {
        Log.e("www","www MyLocationListener init")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startGetLocation(){
        Log.e("www","www MyLocationListener startGetLocation")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopGetLocation(){
        Log.e("www","www MyLocationListener stopGetLocation")
    }

     interface OnLocationChangeListener{
        fun onChanged(latitude: Double,longtude: Double)
    }


}