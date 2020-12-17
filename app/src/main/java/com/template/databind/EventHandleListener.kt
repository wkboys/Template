package com.template.databind

import android.content.Context
import android.view.View
import android.widget.Toast
import com.template.module_common.utils.HToast

class EventHandleListener(  context:Context) {
    var mContext:Context?=null
    init {
        this.mContext=context
    }
    fun onButtonClicked( view:View){
        HToast.showShort("i am clicked")
    }

}