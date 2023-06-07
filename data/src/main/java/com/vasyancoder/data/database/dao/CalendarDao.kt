package com.vasyancoder.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vasyancoder.data.database.model.CalendarItemModel

@Dao
interface CalendarDao {

    @Query("SELECT * FROM CalendarItemModel")
    fun getCalendarItemList(): LiveData<List<CalendarItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCalendarItem(calendarItemModel: CalendarItemModel)

    @Query("DELETE FROM CalendarItemModel WHERE id = :itemId")
    suspend fun deleteCalendarItemById(itemId: Int)
}