package com.vasyancoder.data.database.mapper

import com.vasyancoder.data.database.model.CalendarItemModel
import com.vasyancoder.data.database.model.HackathonModel
import com.vasyancoder.feature_calendar.domain.CalendarItem
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon

object CalendarItemMapper {

    fun mapEntityToDbModel(calendarItem: CalendarItem) = CalendarItemModel(
        id = calendarItem.id,
        name = calendarItem.name,
        organization = calendarItem.organization,
        dates = calendarItem.dates,
        status = calendarItem.status
    )

    fun mapDbModelToEntity(calendarItemModel: CalendarItemModel) = CalendarItem(
        id = calendarItemModel.id,
        name = calendarItemModel.name,
        organization = calendarItemModel.organization,
        dates = calendarItemModel.dates,
        status = calendarItemModel.status
    )

    fun mapListDbModelToListEntity(list: List<CalendarItemModel>) = list.map {
        mapDbModelToEntity(it)
    }
}