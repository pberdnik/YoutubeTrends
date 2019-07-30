package io.github.pberdnik.youtubetrends.videodetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pberdnik.youtubetrends.network.Channel
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import kotlinx.coroutines.launch

class VideoDetailsViewModel(private val videoInfo: VideoInfo, private val app: Application) : ViewModel() {

    private val _video = MutableLiveData<Video>()
    private val _channel = MutableLiveData<Channel>()

    val video: LiveData<Video> = _video
    val channel: LiveData<Channel> = _channel

    init {
        downloadVideoInfo()
    }

    private fun downloadVideoInfo() {
        viewModelScope.launch {
            _video.value = YoutubeDataApi.retrofitService.getVideo(videoInfo.videoId).items[0]
        }
        viewModelScope.launch {
            _channel.value = YoutubeDataApi.retrofitService.getChannel(videoInfo.channelId).items[0]
        }
    }
}
