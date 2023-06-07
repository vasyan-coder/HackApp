package com.vasyancoder.feature_hackathon_list.presentation.tag_list

import androidx.recyclerview.widget.DiffUtil
import com.vasyancoder.feature_hackathon_list.domain.entity.Tag

class TagItemDiffCallback : DiffUtil.ItemCallback<Tag>() {

    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}