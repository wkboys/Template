package com.template.databind

import android.graphics.Color
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.template.wk.R

class RecyclerViewImageBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("itemImage")
        fun setImage(imageView:ImageView,imageUrl:String){
            if(!TextUtils.isEmpty(imageUrl)){
                Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imageView)
            }else{
                imageView.setBackgroundColor(Color.DKGRAY)
            }
        }
    }
}