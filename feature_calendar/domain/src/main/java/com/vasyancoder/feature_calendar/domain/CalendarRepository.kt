package com.vasyancoder.feature_calendar.domain

import androidx.lifecycle.LiveData

interface CalendarRepository {

    fun getCalendarListUseCase(): LiveData<List<CalendarItem>>

    suspend fun addCalendarItemUseCase()

    suspend fun deleteCalendarItemUseCase(id: Int)
}