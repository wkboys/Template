package com.template.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.template.wk.R

class MoviePagedListAdapter(private val context: Context) : PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        /**
         * 用于计算列表中两个非空项之间的差异的回调。
         * 之前数据更新了，需要通过notifyDataSetChanged()通知整个RecyclerView，效率不高
         * 使用DiffUtil只会更新需要更新的Item，不需要刷新整个RecyclerView，并且可以在Item删除的时候加上动画效果
         */
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> = object : DiffUtil.ItemCallback<Movie>() {
            /**
             * 当DiffUtil想要检测两个对象是否代表同一个Item时，调用该方法进行判断
             */
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }
            /**
             * 当DiffUtil想要检测两个Item是否有一样的数据时，调用该方法进行判断
             * 内容如果更新了，展示给用户看的东西可能也需要更新，所以需要这个判断
             */
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            Picasso.get()
                .load(movie.images!!.small)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.ivImage)
            holder.tvTitle.text = movie.title
            holder.tvYear.text = "上映年份:" + movie.year
        } else {
            holder.ivImage.setImageResource(R.drawable.ic_launcher_background)
            holder.tvTitle.text = ""
            holder.tvYear.text = ""
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView
        var tvTitle: TextView
        var tvYear: TextView

        init {
            ivImage = itemView.findViewById(R.id.ivImage)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvYear = itemView.findViewById(R.id.tvYear)
        }
    }
}