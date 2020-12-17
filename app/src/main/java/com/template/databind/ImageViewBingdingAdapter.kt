package com.template.databind

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.template.wk.R

class ImageViewBingdingAdapter {

    companion object{
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView,imageUrl :String){
            if(!TextUtils.isEmpty(imageUrl)){
                Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imageView)
            }else{
                imageView.setBackgroundColor(Color.DKGRAY)
            }
        }

        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView,imageResource :Int){
           imageView.setImageResource(imageResource)
        }

        @JvmStatic
        @BindingAdapter(value=["image","defaultImageResource"],requireAll = false)
        fun setImage(imageView: ImageView,imageUrl :String,imageResource :Int){
            if(!TextUtils.isEmpty(imageUrl)){
                Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imageView)
            }else{
                imageView.setImageResource(imageResource)
            }
        }

        @JvmStatic
        @BindingAdapter("padding")
        fun setPadding(view: View, oldPadding :Int, newPadding :Int){
            if (oldPadding!=newPadding){
                view.setPadding(newPadding,newPadding,newPadding,newPadding)
            }
        }


    }
}