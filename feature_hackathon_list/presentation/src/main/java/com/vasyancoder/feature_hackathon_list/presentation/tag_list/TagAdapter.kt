package com.vasyancoder.feature_hackathon_list.presentation.tag_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasyancoder.feature_hackathon_list.domain.entity.Tag
import com.vasyancoder.feature_hackathon_list.presentation.databinding.ItemTagBinding

class TagAdapter:
    ListAdapter<Tag, TagAdapter.ViewHolder>(TagItemDiffCallback()) {

    inner class ViewHolder(val binding: ItemTagBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemTagBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.textName.text = item.name
        }
    }
}