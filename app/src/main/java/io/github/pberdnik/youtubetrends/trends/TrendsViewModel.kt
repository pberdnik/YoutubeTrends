package io.github.pberdnik.youtubetrends.trends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import kotlinx.coroutines.launch

typealias VideoInfo = Pair<String, String> // videoId and channelId

class TrendsViewModel : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> = _videos

    private val _navigateToSelectedVideo = MutableLiveData<VideoInfo>()
    val navigateToSelectedVideo: LiveData<VideoInfo> = _navigateToSelectedVideo

    init {
        downloadTrends()
    }

    private fun downloadTrends() {
        viewModelScope.launch {
            _videos.value = YoutubeDataApi.retrofitService.getTrends().items
        }
    }

    fun displayVideoDetails() {
        _navigateToSelectedVideo.value = "" to ""
    }

    fun displayVideoDetailsComplete() {
        _navigateToSelectedVideo.value = null
    }
}
