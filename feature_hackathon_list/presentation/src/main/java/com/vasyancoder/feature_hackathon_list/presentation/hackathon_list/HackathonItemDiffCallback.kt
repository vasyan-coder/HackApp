package com.vasyancoder.feature_hackathon_list.presentation.hackathon_list

import androidx.recyclerview.widget.DiffUtil
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon

class HackathonItemDiffCallback : DiffUtil.ItemCallback<Hackathon>() {

    override fun areItemsTheSame(oldItem: Hackathon, newItem: Hackathon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hackathon, newItem: Hackathon): Boolean {
        return oldItem == newItem
    }
}