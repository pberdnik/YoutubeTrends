package io.github.pberdnik.youtubetrends.videodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pberdnik.youtubetrends.network.Channel
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import kotlinx.coroutines.launch

class VideoDetailsViewModel : ViewModel() {

    private val videoId = "gn4li_PFNf4"
    private val channelId = "UCX7g1iDFbAK_S_IPTuWzhOA"

    private val _video = MutableLiveData<Video>()
    private val _channel = MutableLiveData<Channel>()

    val video: LiveData<Video> = _video
    val channel: LiveData<Channel> = _channel

    init {
        downloadVideoInfo()
    }

    private fun downloadVideoInfo() {
        viewModelScope.launch {
            _video.value = YoutubeDataApi.retrofitService.getVideo(videoId).items[0]
        }
        viewModelScope.launch {
            _channel.value = YoutubeDataApi.retrofitService.getChannel(channelId).items[0]
        }
    }
}
