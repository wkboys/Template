package com.template.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.template.mid.MidVM
import com.template.module_common.base.DataBindingConfig
import com.template.vm.TimerViewModel
import com.template.vm.TimerWithLiveDataViewModel
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.LazyVmFragment
import kotlinx.android.synthetic.main.fragment_mid.*

/**
 * A fragment representing a list of Items.
 */
class MidFragment : LazyVmFragment() {

    private var midVM: MidVM? = null

    override fun lazyInit() {
        initView()
        loadData()
    }
    override fun getLayoutId() = R.layout.fragment_mid

    override fun initViewModel() {
        midVM = getActivityViewModel(MidVM::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_mid, midVM)
            .addBindingParam(BR._all, midVM)
    }

    override fun initView() {
        super.initView()
     /*   var timerViewModel:TimerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        timerViewModel.setonTimeChangeListener(object :TimerViewModel.onTimeChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onTimeChanged(second: Int) {
                activity?.runOnUiThread { tv_mid.text = "Time: $second" }
            }
        })
        timerViewModel.startTiming()*/

        var timerWithLiveDataViewModel:TimerWithLiveDataViewModel=ViewModelProvider(this).get(TimerWithLiveDataViewModel::class.java)
        val liveData = timerWithLiveDataViewModel.getCurrentSecond()
        liveData!!.observe(this,object : Observer<Int> {
            override fun onChanged(t: Int?) {
                tv_mid.text= "Time: $t"
            }
        })
        rest_time.setOnClickListener {
            liveData.value=0
        }
        timerWithLiveDataViewModel.startTiming()

    }
}