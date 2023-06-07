package com.vasyancoder.feature_calendar.domain

import androidx.lifecycle.LiveData

class GetCalendarListUseCase(
    private val repository: CalendarRepository
) {

    operator fun invoke(): LiveData<List<CalendarItem>> {
        return repository.getCalendarListUseCase()
    }
}