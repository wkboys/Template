package com.template.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.template.module_common.base.BaseViewModel
import java.util.*

class ShareDataViewModel : BaseViewModel() {

    private var progress: MutableLiveData<Int> ?=null
    fun getProgress(): MutableLiveData<Int>? {
        if (progress==null){
            progress= MutableLiveData()
        }
        return progress
    }

    override fun onCleared() {
        super.onCleared()
        progress=null
    }

}