package com.template.fragment

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.template.service.MyService
import com.template.home.HomeVM
import com.template.listener.MyLocationListener
import com.template.module_common.base.DataBindingConfig
import com.template.room.MyDatabase
import com.template.room.Student
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.LazyVmFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A fragment representing a list of Items.
 */
class HomeFragment : LazyVmFragment() {

    private var homeVm: HomeVM? = null
    private var myLocationListener: MyLocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myLocationListener= context?.let {
            MyLocationListener(it, object: MyLocationListener.OnLocationChangeListener{
                override fun onChanged(latitude: Double, longtude: Double) {
                    Log.e("www","www MyLocationListener onChanged")
                }
            })
        }
        myLocationListener?.let { lifecycle.addObserver(it) }
    }

    override fun lazyInit() {
        initView()
        loadData()
    }

    override fun initView() {
        super.initView()
        start_btn.setOnClickListener {
            var intent:Intent=Intent(activity,MyService().javaClass)
            val startService: ComponentName? = activity?.startService(intent)
        }
        stop_btn.setOnClickListener {
            var intent:Intent=Intent(activity,MyService().javaClass)
            val stopService = activity?.stopService(intent)

        }
        var student: Student= Student(0,"学生")
        var myDatabase: MyDatabase = MyDatabase.getDBInstace()
        myDatabase.getStudentDao().insertStudent(student)
        val studentList = myDatabase.getStudentDao().getStudentList()

    }

    override fun getLayoutId() = R.layout.fragment_home

    override fun initViewModel() {
        homeVm = getActivityViewModel(HomeVM::class.java)

    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_home, homeVm)
            .addBindingParam(BR._all, homeVm)
    }

}