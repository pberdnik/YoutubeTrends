package io.github.pberdnik.youtubetrends

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.pberdnik.youtubetrends.network.YoutubeDataApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            tvMain.text = YoutubeDataApi.retrofitService.getTrends()
        }
    }
}
