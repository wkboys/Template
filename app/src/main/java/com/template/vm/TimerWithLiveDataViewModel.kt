package com.template.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TimerWithLiveDataViewModel : ViewModel() {

    private var currentSecond: MutableLiveData<Int> ?=null
    fun getCurrentSecond(): MutableLiveData<Int>? {
        if (currentSecond==null){
            currentSecond= MutableLiveData()
        }
        return currentSecond
    }

    var timer:Timer?=null
    fun startTiming(){
        if (timer==null){
            var current=0
            timer=Timer()
            var timerTask:TimerTask=object : TimerTask(){
                override fun run() {
                    if (currentSecond!!.value==0){
                        current=0
                    }
                    ++current
                    currentSecond!!.postValue(current)
                }
            }
            timer!!.schedule(timerTask,1000,1000)
        }
    }
}