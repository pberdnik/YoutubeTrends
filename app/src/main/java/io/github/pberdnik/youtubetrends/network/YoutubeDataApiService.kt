package io.github.pberdnik.youtubetrends.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.pberdnik.youtubetrends.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

interface YoutubeDataApiService {
    @GET("videos")
    suspend fun getTrends(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = 25,
        @Query("regionCode") regionCode: String = "RU"
    ): Videos

    @GET("videos")
    suspend fun getVideo(
        @Query("id") id: String,
        @Query("part") part: String = "snippet,statistics"
    ): Videos

    @GET("channels")
    suspend fun getChannel(
        @Query("id") id: String,
        @Query("part") part: String = "snippet,statistics"
    ): Channels
}

object YoutubeDataApi {
    val retrofitService = retrofit.create(YoutubeDataApiService::class.java)
}


// add apiKey query parameter to each request
val apiKeyInterceptor = object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("key", BuildConfig.YouTubeApiKey)
            .build()
        val request = chain.request().newBuilder()
            // .addHeader("Authorization", "Bearer token")
            .url(url)
            .build()
        return chain.proceed(request)
    }
}

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val okHttpClient = OkHttpClient().newBuilder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor(apiKeyInterceptor)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
