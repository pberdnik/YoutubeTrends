package io.github.pberdnik.youtubetrends.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import io.github.pberdnik.youtubetrends.databinding.TrendsFragmentBinding

class TrendsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(TrendsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = TrendsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.trendsList.adapter = TrendsAdapter(TrendsAdapter.OnClickListener {video ->
            viewModel.displayVideoDetails(video)
        })
        viewModel.navigateToSelectedVideo.observe(this, Observer {video ->
            video?.let {
                findNavController().navigate(TrendsFragmentDirections
                    .actionTrendsFragmentToVideoDetailsFragment(video.id, video.snippet.channelId))
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayVideoDetailsComplete()
            }
        })
        return binding.root
    }

}
