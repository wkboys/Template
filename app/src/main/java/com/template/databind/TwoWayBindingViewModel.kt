package com.template.databind

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField

class TwoWayBindingViewModel {
    var loginModelObservableField:ObservableField<LoginModel>?=null
    init {
        var loginModel= LoginModel()
        loginModel.userName="Michael"
        loginModelObservableField=ObservableField<LoginModel>()
        loginModelObservableField!!.set(loginModel)
    }

//    @Bindable
    fun getUserName(): String? {
        return loginModelObservableField?.get()?.userName
    }

    fun setUserName(userName:String){
        loginModelObservableField?.get()?.userName =userName
        Log.e("zzz","userName="+userName)
//        if (userName!=null && !userName.equals(loginModel?.userName)){
//            loginModel!!.userName=userName
//            //可在此处理一些业务与相关逻辑 比如保存
//            Log.e("zzz","userName="+userName)
//            notifyChange()
//        }
    }

}