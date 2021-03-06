package com.template.pagingpage

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
import com.template.pagingpage.UserAdapter.UserViewHolder
import com.template.wk.R

class UserAdapter(private val context: Context) : PagedListAdapter<User, UserViewHolder>(
    DIFF_CALLBACK
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            Picasso.get()
                .load(user.avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.ivAvatar)
            holder.tvName.text = user.name
        } else {
            holder.ivAvatar.setImageResource(R.drawable.ic_launcher_background)
            holder.tvName.text = ""
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivAvatar: ImageView
        var tvName: TextView

        init {
            ivAvatar = itemView.findViewById(R.id.ivAvatar)
            tvName = itemView.findViewById(R.id.tvName)
        }
    }

    companion object {
        /**
         * 用于计算列表中两个非空项之间的差异的回调。
         *
         * 之前数据更新了，需要通过notifyDataSetChanged()通知整个RecyclerView，效率不高
         * 使用DiffUtil只会更新需要更新的Item，不需要刷新整个RecyclerView，并且可以在Item删除的时候加上动画效果
         */
        val DIFF_CALLBACK: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                /**
                 * 当DiffUtil想要检测两个对象是否代表同一个Item时，调用该方法进行判断
                 */
                override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem.id == newItem.id
                }

                /**
                 * 当DiffUtil想要检测两个Item是否有一样的数据时，调用该方法进行判断
                 *
                 * 内容如果更新了，展示给用户看的东西可能也需要更新，所以需要这个判断
                 */
                override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }
}