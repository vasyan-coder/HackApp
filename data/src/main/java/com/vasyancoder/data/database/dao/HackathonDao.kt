package com.vasyancoder.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vasyancoder.data.database.model.HackathonModel

@Dao
interface HackathonDao {

    @Query("SELECT * FROM HackathonModel WHERE (:tag = '' OR status = :tag)")
    fun getHackathonList(tag: String): LiveData<List<HackathonModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHackathon(hackathon: HackathonModel)
}
