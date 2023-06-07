package com.vasyancoder.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vasyancoder.data.CalendarRepositoryImpl
import com.vasyancoder.feature_calendar.domain.AddCalendarItemUseCase
import com.vasyancoder.feature_calendar.domain.GetCalendarListUseCase

class CalendarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CalendarRepositoryImpl(application)

    private val getCalendarListUseCase = GetCalendarListUseCase(repository)
    private val addCalendarListUseCase = AddCalendarItemUseCase(repository)

    val calendarList = getCalendarListUseCase()
}