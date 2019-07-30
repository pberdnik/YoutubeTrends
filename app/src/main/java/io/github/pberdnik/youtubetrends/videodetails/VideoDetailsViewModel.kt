package io.github.pberdnik.youtubetrends.videodetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.pberdnik.youtubetrends.network.Channel
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import io.github.pberdnik.youtubetrends.util.YouTubeApiStatus
import io.github.pberdnik.youtubetrends.util.performLongOperation

class VideoDetailsViewModel(private val videoInfo: VideoInfo, private val app: Application) : ViewModel() {

    private val _video = MutableLiveData<Video>()
    private val _channel = MutableLiveData<Channel>()

    val video: LiveData<Video> = _video
    val channel: LiveData<Channel> = _channel

    val status = MutableLiveData<YouTubeApiStatus>()

    init {
        downloadVideoInfo()
    }

    private fun downloadVideoInfo() {
        performLongOperation(status) {
            _video.value = YoutubeDataApi.retrofitService.getVideo(videoInfo.videoId).items[0]
        }
        performLongOperation(status) {
            _channel.value = YoutubeDataApi.retrofitService.getChannel(videoInfo.channelId).items[0]
        }
    }
}
