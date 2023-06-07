package com.vasyancoder.presentation.calendar_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vasyancoder.feature_calendar.domain.CalendarItem
import com.vasyancoder.presentation.databinding.ItemCalendarBinding

class CalendarAdapter :
    ListAdapter<CalendarItem, CalendarAdapter.ViewHolder>(CalendarItemDiffCallback()) {

    inner class ViewHolder(val binding: ItemCalendarBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemCalendarBinding.inflate(
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
            binding.textDate.text = item.dates
        }
    }
}