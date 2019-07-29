package io.github.pberdnik.youtubetrends.videodetails

import androidx.lifecycle.*
import io.github.pberdnik.youtubetrends.network.Channel
import io.github.pberdnik.youtubetrends.network.Thumbnails
import io.github.pberdnik.youtubetrends.network.Video
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import kotlinx.coroutines.launch

class VideoDetailsViewModel : ViewModel() {

    private val videoId = "gn4li_PFNf4"
    private val channelId = "UCX7g1iDFbAK_S_IPTuWzhOA"

    private val _video = MutableLiveData<Video>()
    private val _channel = MutableLiveData<Channel>()

    val videoThumbnailUrl: LiveData<String> = Transformations.map(_video) { it.snippet.thumbnails.best }
    val videoTitle: LiveData<String> = Transformations.map(_video) { it.snippet.title }
    val videoDescription: LiveData<String> = Transformations.map(_video) { it.snippet.description }
    val videoViewCount: LiveData<String> = Transformations.map(_video) { it.statistics?.viewCount }

    val channelAvatarUrl: LiveData<String> = Transformations.map(_channel) { it.snippet.thumbnails.default?.url }
    val channelTitle: LiveData<String> = Transformations.map(_channel) { it.snippet.title }
    val channelSubscriberCount: LiveData<String> = Transformations.map(_channel) { it.statistics.subscriberCount }

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

private val Thumbnails.best: String
    get() {
        return (maxres ?: standard ?: high ?: medium ?: default)!!.url
    }