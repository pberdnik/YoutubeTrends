package io.github.pberdnik.youtubetrends.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.github.pberdnik.youtubetrends.databinding.TrendsFragmentBinding

class TrendsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(TrendsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = TrendsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.trendsList.adapter = TrendsAdapter(TrendsAdapter.OnClickListener {

        })
        return binding.root
    }

}
