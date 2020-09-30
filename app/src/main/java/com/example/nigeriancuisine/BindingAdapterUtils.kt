package com.example.nigeriancuisine

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Food>?) {
    val adapter = recyclerView.adapter as FoodAdapter
    adapter.submitList(data)
    // Some are adding this line while others are not adding it
    //recyclerView.scrollToPosition(0)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_cloud)
                .error(R.drawable.ic_cloud))
            .into(imgView)
    }
}