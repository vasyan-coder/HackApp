package com.vasyancoder.feature_hackathon_list.presentation.hackathon_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon
import com.vasyancoder.feature_hackathon_list.presentation.databinding.ItemHackathonBinding

class HackathonsAdapter :
    ListAdapter<Hackathon, HackathonsAdapter.ViewHolder>(HackathonItemDiffCallback()) {

    inner class ViewHolder(val binding: ItemHackathonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemHackathonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            binding.textTitle.text = item.name
            binding.textOrganization.text = item.organization
            binding.textDates.text = item.dates
            binding.textStatus.text = item.status
        }
    }

}