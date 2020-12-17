package com.template.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.databind.*
import com.template.module_common.utils.HToast
import com.template.wk.BR
import com.template.wk.R
import com.template.wk.databinding.ActivityDatabindBinding

class DataBindActivity : AppCompatActivity() {
     var activityDatabindBinding:ActivityDatabindBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         activityDatabindBinding= DataBindingUtil.setContentView<ActivityDatabindBinding>(this, R.layout.activity_databind)
        activityDatabindBinding!!.book= Book("123456","123456",1)
        activityDatabindBinding!!.eventHandler= EventHandleListener(this)
        activityDatabindBinding!!.networkImage="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608186946041&di=576875cd4289a49c1a6d22602db3a321&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F01%2F12%2F51%2F8956f6057d53239.jpg"
        activityDatabindBinding!!.localImage=R.drawable.ic_launcher_foreground
//        activityDatabindBinding.networkImage=""
        activityDatabindBinding!!.imagePadding=40
        activityDatabindBinding!!.clickHandler= ClickHandler()
        activityDatabindBinding!!.viewModel= TwoWayBindingViewModel()

        activityDatabindBinding!!.recyclerView.layoutManager=LinearLayoutManager(this)
        activityDatabindBinding!!.recyclerView.setHasFixedSize(true)
        var adapter:RecyclerViewAdapter= RecyclerViewAdapter(RecyclerViewViewModel().getBooks(),this)
        activityDatabindBinding!!.recyclerView.adapter=adapter
    }

    inner class ClickHandler{
        fun onClicked( view: View){
            activityDatabindBinding!!.imagePadding=180
        }
    }
}