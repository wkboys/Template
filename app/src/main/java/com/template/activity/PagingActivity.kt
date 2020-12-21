package com.template.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.template.pagingpage.User
import com.template.pagingpage.UserAdapter
import com.template.pagingpage.UserViewModel
import com.template.wk.R

class PagingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val userAdapter = UserAdapter(this)
        val movieViewModel: UserViewModel =
            ViewModelProviders.of(this).get(UserViewModel::class.java)
        movieViewModel.userPagedList.observe(this,
            Observer<PagedList<User>> { users ->
                Log.e("MainActivity", "onChange()->$users")
                userAdapter.submitList(users)
            })
        recyclerView.adapter = userAdapter
    }
}