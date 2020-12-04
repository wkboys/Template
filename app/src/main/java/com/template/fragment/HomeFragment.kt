package com.template.fragment

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.home.HomeVM
import com.template.listener.MyLocationListener
import com.template.module_common.base.DataBindingConfig
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.BaseVmFragment
import com.zs.base_library.base.LazyVmFragment

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
    override fun getLayoutId() = R.layout.fragment_home

    override fun initViewModel() {
        homeVm = getActivityViewModel(HomeVM::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_home, homeVm)
            .addBindingParam(BR._all, homeVm)
    }

}