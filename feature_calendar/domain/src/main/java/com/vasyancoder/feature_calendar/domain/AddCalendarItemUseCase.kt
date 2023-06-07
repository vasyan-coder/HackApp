package com.vasyancoder.feature_calendar.domain

class AddCalendarItemUseCase(
    private val repository: CalendarRepository
) {

    suspend operator fun invoke() {
        repository.addCalendarItemUseCase()
    }
}