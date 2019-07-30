package io.github.pberdnik.youtubetrends.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

enum class YouTubeApiStatus { LOADING, ERROR, DONE }

fun ViewModel.performLongOperation(status: MutableLiveData<YouTubeApiStatus>, block: suspend () -> Unit) {
    viewModelScope.launch {
        try {
            status.value = YouTubeApiStatus.LOADING
            block()
            status.value = YouTubeApiStatus.DONE
        } catch (e: Exception) {
            status.value = YouTubeApiStatus.ERROR
        }
    }
}