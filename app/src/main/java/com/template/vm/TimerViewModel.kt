package com.template.vm

import androidx.lifecycle.ViewModel
import java.util.*

 class TimerViewModel : ViewModel() {
    var timer:Timer?=null
    var onTime : onTimeChangeListener?=null
    fun startTiming(){
        if (timer==null){
            var current=0
            timer=Timer()
            var timerTask:TimerTask=object : TimerTask(){
                override fun run() {
                    ++current
                    if (onTime!=null){
                        onTime!!.onTimeChanged(current!!)
                    }
                }
            }
            timer!!.schedule(timerTask,1000,1000)
        }
    }

    interface onTimeChangeListener{
        fun onTimeChanged(second:Int)
    }
    fun setonTimeChangeListener(ontime:onTimeChangeListener){
        onTime=ontime
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

}