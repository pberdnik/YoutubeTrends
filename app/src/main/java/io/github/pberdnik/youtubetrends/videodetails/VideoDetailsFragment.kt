package io.github.pberdnik.youtubetrends.videodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.pberdnik.youtubetrends.databinding.VideoDetailsFragmentBinding

class VideoDetailsFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(VideoDetailsViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = VideoDetailsFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}
