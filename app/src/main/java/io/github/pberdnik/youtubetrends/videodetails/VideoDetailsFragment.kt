package io.github.pberdnik.youtubetrends.videodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.pberdnik.youtubetrends.R

class VideoDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = VideoDetailsFragment()
    }

    private lateinit var viewModel: VideoDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.video_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VideoDetailsViewModel::class.java)
    }

}
