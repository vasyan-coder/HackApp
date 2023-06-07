package com.vasyancoder.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vasyancoder.data.CalendarRepositoryImpl
import com.vasyancoder.feature_calendar.domain.AddCalendarItemUseCase
import com.vasyancoder.feature_calendar.domain.DeleteCalendarItemUseCase
import com.vasyancoder.feature_calendar.domain.GetCalendarListUseCase
import kotlinx.coroutines.launch

class CalendarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CalendarRepositoryImpl(application)

    private val getCalendarListUseCase = GetCalendarListUseCase(repository)
    private val addCalendarListUseCase = AddCalendarItemUseCase(repository)
    private val deleteCalendarItemUseCase = DeleteCalendarItemUseCase(repository)

    val calendarList = getCalendarListUseCase()

    fun deleteCalendarItem(id: Int) {
        viewModelScope.launch {
            deleteCalendarItemUseCase(id)
        }
    }
}