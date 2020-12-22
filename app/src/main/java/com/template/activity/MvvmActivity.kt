package com.template.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.template.mvvm.AnthorUserViewModel
import com.template.mvvm.AuthorUser
import com.template.wk.R
import com.template.wk.databinding.ActivityMvvmBinding

class MvvmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMvvmBinding>(this, R.layout.activity_mvvm)
        val userViewModel: AnthorUserViewModel =
            ViewModelProviders.of(this).get(AnthorUserViewModel::class.java)
        userViewModel.getUser().observe(this, Observer<AuthorUser> { user ->
            Log.e("MainActivity->onChanged", "user:$user")
            if (user != null) {
                activityMainBinding.user = user
            }
        })

        val swipeRefresh: SwipeRefreshLayout = activityMainBinding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            userViewModel.refresh()
            swipeRefresh.isRefreshing = false
        }

    }
}