package com.vasyancoder.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vasyancoder.data.database.model.HackathonModel

interface HackathonDao {

    @Query(" SELECT * FROM Hackathon WHERE (:tag = '' OR tag = :tag)")
    fun getHackathonList(): LiveData<List<HackathonModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHackathon(hackathon: HackathonModel)
}
