package com.template.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.template.module_common.base.DataBindingConfig
import com.template.module_common.common.initFragment
import com.template.wk.BR
import com.template.wk.R
import com.zs.base_library.base.BaseVmFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A fragment representing a list of Items.
 */
class MainFragment : BaseVmFragment() {

    private val fragmentList = arrayListOf<Fragment>()

    private val homeFragment by lazy { HomeFragment() }

    private val midFragment by lazy { MidFragment() }

    private val setFragment by lazy { SetFragment() }

    init {
        fragmentList.apply {
            add(homeFragment)
            add(midFragment)
            add(setFragment)
        }
    }

    override fun init(savedInstanceState: Bundle?) {
        //初始化viewpager2
        vpHome.initFragment(this, fragmentList).run {
            //全部缓存,避免切换回重新加载
            offscreenPageLimit = fragmentList.size
        }
        //取消viewPager2滑动
        vpHome.isUserInputEnabled = false
        vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btnNav.menu.getItem(position).isChecked = true
            }
        })
        //初始化底部导航栏
        btnNav.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> vpHome.setCurrentItem(0, false)
                    R.id.menu_mid -> vpHome.setCurrentItem(1, false)
                    R.id.menu_set -> vpHome.setCurrentItem(2, false)
                }
                // 这里注意返回true,否则点击失效
                true
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_main


    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_main, null)
            .addBindingParam(BR._all, null)
    }
}