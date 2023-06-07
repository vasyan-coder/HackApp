package com.vasyancoder.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vasyancoder.data.database.HackAppDatabase
import com.vasyancoder.data.database.mapper.CalendarItemMapper
import com.vasyancoder.feature_calendar.domain.CalendarItem
import com.vasyancoder.feature_calendar.domain.CalendarRepository

class CalendarRepositoryImpl(
    private val context: Context
) : CalendarRepository {

    private val db = HackAppDatabase.getDatabase(context)
    private val calendarDao = db.calendarDao()

    override fun getCalendarListUseCase(): LiveData<List<CalendarItem>> = Transformations.map(
        calendarDao.getCalendarItemList()
    ) {
        CalendarItemMapper.mapListDbModelToListEntity(it)
    }


    override suspend fun addCalendarItemUseCase() {
    }

    override suspend fun deleteCalendarItemUseCase(id: Int) {
        calendarDao.deleteCalendarItemById(id)
    }
}