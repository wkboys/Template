package com.template.fragment

import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.template.module_common.base.DataBindingConfig
import com.template.vm.ShareDataViewModel
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.LazyVmFragment
import kotlinx.android.synthetic.main.fragment_one.*

/**
 * A fragment representing a list of Items.
 */
class OneFragment : LazyVmFragment() {
    private var shareVm: ShareDataViewModel? = null

    override fun lazyInit() {
        initView()
        loadData()
    }
    override fun getLayoutId() = R.layout.fragment_one

    override fun initViewModel() {
        shareVm = getActivityViewModel(ShareDataViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_one, shareVm)
            .addBindingParam(BR._all, shareVm)
    }

    override fun initView() {
        super.initView()
        val liveData = shareVm!!.getProgress()
        liveData!!.observe(this,object: Observer<Int>{
            override fun onChanged(t: Int?) {
                t?.let { seekBar.progress = it }
            }
        })
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                liveData.value=progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}