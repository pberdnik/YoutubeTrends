package io.github.pberdnik.youtubetrends.videodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.pberdnik.youtubetrends.databinding.VideoDetailsFragmentBinding

class VideoDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = VideoDetailsFragmentBinding.inflate(inflater)
        val args = VideoDetailsFragmentArgs.fromBundle(arguments!!)
        val viewModelFactory = VideoDetailsViewModelFactory(VideoInfo(args.videoId, args.channelId), application)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(VideoDetailsViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}
