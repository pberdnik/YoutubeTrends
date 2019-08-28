package io.github.pberdnik.youtubetrends.videodetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

data class VideoInfo(val videoId: String, val channelId: String)

class VideoDetailsViewModelFactory(
    private val videoInfo: VideoInfo,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VideoDetailsViewModel::class.java)) {
            return VideoDetailsViewModel(videoInfo, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}