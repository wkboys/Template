package com.template.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.home.HomeVM
import com.template.module_common.base.DataBindingConfig
import com.template.set.SetVM
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.BaseVmFragment
import com.zs.base_library.base.LazyVmFragment

/**
 * A fragment representing a list of Items.
 */
class SetFragment : LazyVmFragment() {
    private var setVM: SetVM? = null

    override fun lazyInit() {
        initView()
        loadData()
    }
    override fun getLayoutId() = R.layout.fragment_set

    override fun initViewModel() {
        setVM = getActivityViewModel(SetVM::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_set, setVM)
            .addBindingParam(BR._all, setVM)
    }

}