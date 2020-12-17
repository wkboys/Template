package com.template.fragment

import com.template.databind.BookViewModel
import com.template.databind.Book
import com.template.module_common.base.DataBindingConfig
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.LazyVmFragment

/**
 * A fragment representing a list of Items.
 */
class DataBindingFragment : LazyVmFragment() {
    private var bookVm: BookViewModel? = null
    override fun lazyInit() {
        initView()
        loadData()
    }
    override fun getLayoutId() = R.layout.fragment_databind

    override fun initViewModel() {
        bookVm = getFragmentViewModel(BookViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_databind, bookVm)
            .addBindingParam(BR._all, bookVm)
    }

    override fun initView() {
        super.initView()
        bookVm?.setBook(Book("1234567","1234567",1))

    }
}