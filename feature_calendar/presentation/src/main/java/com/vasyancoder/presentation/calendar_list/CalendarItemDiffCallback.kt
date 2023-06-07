package com.vasyancoder.presentation.calendar_list

import androidx.recyclerview.widget.DiffUtil
import com.vasyancoder.feature_calendar.domain.CalendarItem

class CalendarItemDiffCallback : DiffUtil.ItemCallback<CalendarItem>() {

    override fun areItemsTheSame(oldItem: CalendarItem, newItem: CalendarItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CalendarItem, newItem: CalendarItem): Boolean {
        return oldItem == newItem
    }
}