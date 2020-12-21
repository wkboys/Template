package com.template.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.template.boundary.UserBoundAdapter
import com.template.boundary.UserBoundViewModel
import com.template.boundary.Users
import com.template.wk.R

class BoundaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boundary)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val userAdapter = UserBoundAdapter(this)
        val UserBoundViewModel = ViewModelProviders.of(this)[UserBoundViewModel::class.java]
        UserBoundViewModel.userPagedList.observe(this,
               Observer<PagedList<Users> > { it:PagedList<Users>  ->
                Log.e("MainActivity", "onChange()->$it")
                userAdapter.submitList(it)
            })
        recyclerView.adapter = userAdapter

        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            UserBoundViewModel.refresh()
            swipeRefresh.isRefreshing = false
        }

    }
}