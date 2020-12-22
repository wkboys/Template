package com.template.mvvm

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.template.wk.R

class AnthorImageViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["images", "defaultImageResource"], requireAll = false)
        open fun setImage(imageView: ImageView, imageUrl: String?, imageResource: Int) {
            if (!TextUtils.isEmpty(imageUrl)) {
                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
            } else {
                imageView.setImageResource(imageResource)
            }
        }
    }
}