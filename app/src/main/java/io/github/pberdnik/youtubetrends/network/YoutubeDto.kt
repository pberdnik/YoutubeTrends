package io.github.pberdnik.youtubetrends.network


data class Videos(val items: List<Video>)

data class Video(val id: String, val snippet: Snippet)

data class Snippet(
    val publishedAt: String,
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String
)

data class Thumbnails(
    val default: Thumbnail? = null,
    val medium: Thumbnail? = null,
    val high: Thumbnail? = null,
    val standard: Thumbnail? = null,
    val maxres: Thumbnail? = null
)

data class Thumbnail(
    val url: String,
    val width: Int,
    val height: Int
)
