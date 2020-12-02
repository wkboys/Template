package com.template.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.mid.MidVM
import com.template.module_common.base.DataBindingConfig
import com.template.set.SetVM
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.BaseVmFragment
import com.zs.base_library.base.LazyVmFragment

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
}