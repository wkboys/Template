package com.template.databind

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.template.wk.BR
import com.template.wk.R

class RecyclerViewAdapter(val books: MutableList<Book>, var context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent?.context),  R.layout.recyclerview_databind_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var binding : ViewDataBinding = holder.dataBinding
        binding.setVariable(BR.book,books?.get(position))

    }

    override fun getItemCount(): Int {
        return books?.size!!
    }

     class MyViewHolder(var dataBinding: ViewDataBinding): RecyclerView.ViewHolder(dataBinding.root)
    {
    }
}