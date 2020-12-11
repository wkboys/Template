package com.template.service


import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService

class MyService : LifecycleService() {

    init{
        var myServiceObserver: MyServiceObserver = MyServiceObserver()
        lifecycle.addObserver(myServiceObserver)
    }

    override fun onBind(intent: Intent): IBinder? {
        return super.onBind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

}