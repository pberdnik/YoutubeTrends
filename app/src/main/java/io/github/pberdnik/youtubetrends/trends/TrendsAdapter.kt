/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.pberdnik.youtubetrends.trends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.pberdnik.youtubetrends.databinding.TrendsItemBinding
import io.github.pberdnik.youtubetrends.network.Video

class TrendsAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Video, TrendsAdapter.VideoViewHolder>(DiffCallback) {

    class VideoViewHolder(private var binding: TrendsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video) {
            binding.video = video
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Video]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(TrendsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(video)
        }
        holder.bind(video)
    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Video]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Video]
     */
    class OnClickListener(val clickListener: (video: Video) -> Unit) {
        fun onClick(video: Video) = clickListener(video)
    }
}
