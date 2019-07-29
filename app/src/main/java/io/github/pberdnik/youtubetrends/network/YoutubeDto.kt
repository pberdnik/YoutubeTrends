package io.github.pberdnik.youtubetrends.network


data class Videos(val items: List<Video>)

data class Video(
    val id: String,
    val snippet: VideoSnippet,
    val statistics: VideoStatistics? = null
)

data class VideoSnippet(
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

data class VideoStatistics(val viewCount: String)


data class Channels(val items: List<Channel>)

data class Channel(
    val id: String,
    val snippet: ChanelSnippet,
    val statistics: ChanelStatistics
)

data class ChanelSnippet(
    val title: String,
    val thumbnails: Thumbnails
)

data class ChanelStatistics(val subscriberCount: String)