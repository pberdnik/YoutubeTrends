package io.github.pberdnik.youtubetrends.trends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import kotlinx.coroutines.launch


class TrendsViewModel : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> = _videos

    private val _navigateToSelectedVideo = MutableLiveData<Video>()
    val navigateToSelectedVideo: LiveData<Video> = _navigateToSelectedVideo

    init {
        downloadTrends()
    }

    private fun downloadTrends() {
        viewModelScope.launch {
            _videos.value = YoutubeDataApi.retrofitService.getTrends().items
        }
    }

    fun displayVideoDetails(video: Video) {
        _navigateToSelectedVideo.value = video
    }

    fun displayVideoDetailsComplete() {
        _navigateToSelectedVideo.value = null
    }
}
