package com.template.fragment

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.solver.state.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.template.activity.BoundaryActivity
import com.template.activity.DataBindActivity
import com.template.activity.MvvmActivity
import com.template.activity.PagingActivity
import com.template.service.MyService
import com.template.home.HomeVM
import com.template.listener.MyLocationListener
import com.template.module_common.base.DataBindingConfig
import com.template.room.MyDatabase
import com.template.room.Student
import com.template.room.StudentViewModel
import com.template.wk.BR
import com.template.wk.R
import com.template.worker.UploadLogWorker
import com.zs.base_library.base.LazyVmFragment
import kotlinx.android.synthetic.main.fragment_home.*
import okio.Timeout
import java.util.concurrent.TimeUnit

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

    @SuppressLint("RestrictedApi")
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
        jimp_btn.setOnClickListener {
//            nav().navigate(R.id.action_home_fragment_to_databind_fragment)
            val intent = Intent(context, DataBindActivity::class.java)
            startActivity(intent)
        }
        paging_btn.setOnClickListener {
            val intent = Intent(context, PagingActivity::class.java)
            startActivity(intent)
        }
        boundary_btn.setOnClickListener {
            val intent = Intent(context, BoundaryActivity::class.java)
            startActivity(intent)
        }
        mvvm_btn.setOnClickListener {
            val intent = Intent(context, MvvmActivity::class.java)
            startActivity(intent)
        }

//        var student: Student= Student(4,"学生")
//        var myDatabase: MyDatabase = MyDatabase.getDBInstace()
//        myDatabase.getStudentDao().insertStudent(student)
//        val studentList = myDatabase.getStudentDao().getStudentList()
//        Log.e("","")
        var studentList:MutableList<Student> = ArrayList<Student>()
        var studentViewModel:StudentViewModel=ViewModelProvider(this).get(StudentViewModel::class.java)
        studentViewModel.getLiveDataStudent()?.observe(this,object : Observer<List<Student>> {
            override fun onChanged(t: List<Student>?) {
                studentList.clear()
                t?.let { studentList.addAll(it) }
                Log.e("www","student List="+studentList.toString())
            }
        })
        //也可从asset文件夹中导入的.db文件创建数据库 略过
        var constraints:Constraints=Constraints.Builder().setRequiresCharging(true).
        setRequiredNetworkType(NetworkType.CONNECTED).setRequiresBatteryNotLow(true).build()
        //数据不能超过10KB
        var inputData:Data=Data.Builder().put("input_data","Hello World").build()

        var uploadWorkRequest:OneTimeWorkRequest =OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
            .setConstraints(constraints)//设置触发条件
            .setInitialDelay(10,TimeUnit.SECONDS)
            .setBackoffCriteria(BackoffPolicy.LINEAR,OneTimeWorkRequest.MIN_BACKOFF_MILLIS,TimeUnit.MILLISECONDS)
            .addTag("upload")
            .setInputData(inputData)
            .build()
        activity?.let {
            WorkManager.getInstance(it).enqueue(uploadWorkRequest)
            //任务链 周期任务
//            WorkManager.getInstance(it).beginWith(uploadWorkRequest,).then(uploadWorkRequest)
//            var list:List<WorkContinuation> =ArrayList<WorkContinuation>()
//            WorkContinuation.combine(list).then(WorkRequestE).enqueue
            val workInfosByTag = WorkManager.getInstance(it).getWorkInfosByTag("upload")
            WorkManager.getInstance(it).getWorkInfoByIdLiveData(uploadWorkRequest.id).
            observe(this,object:Observer<WorkInfo> {
                override fun onChanged(work: WorkInfo) {
                    if (work!=null&&work.state==WorkInfo.State.SUCCEEDED){
                        val outputData = work.outputData.getString("outputData")
                    }
                }
            })
//            WorkManager.getInstance(it).cancelAllWork()
        }
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