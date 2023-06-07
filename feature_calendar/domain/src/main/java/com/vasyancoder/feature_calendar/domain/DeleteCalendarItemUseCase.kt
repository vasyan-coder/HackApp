package com.vasyancoder.feature_calendar.domain

class DeleteCalendarItemUseCase(
    private val repository: CalendarRepository
) {

    suspend operator fun invoke(id: Int) {
        repository.deleteCalendarItemUseCase(id)
    }
}