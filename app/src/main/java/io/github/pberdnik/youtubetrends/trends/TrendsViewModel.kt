package io.github.pberdnik.youtubetrends.trends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import io.github.pberdnik.youtubetrends.util.YouTubeApiStatus
import io.github.pberdnik.youtubetrends.util.performLongOperation

class TrendsViewModel : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> = _videos

    private val _navigateToSelectedVideo = MutableLiveData<Video>()
    val navigateToSelectedVideo: LiveData<Video> = _navigateToSelectedVideo

    val status = MutableLiveData<YouTubeApiStatus>()

    init {
        downloadTrends()
    }

    private fun downloadTrends() {
        performLongOperation(status) {
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
