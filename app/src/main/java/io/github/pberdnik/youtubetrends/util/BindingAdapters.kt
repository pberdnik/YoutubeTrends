package io.github.pberdnik.youtubetrends.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.pberdnik.youtubetrends.R
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.trends.TrendsAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String? = null) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Video>?) {
    val adapter = recyclerView.adapter as TrendsAdapter
    adapter.submitList(data)
}

@BindingAdapter("youTubeApiStatus")
fun bindStatus(statusImageView: ImageView, status: YouTubeApiStatus?) {
    when (status) {
        YouTubeApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        YouTubeApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        YouTubeApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}