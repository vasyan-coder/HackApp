package com.vasyancoder.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HackathonModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val organization: String,
    val dates: String,
    val status: String,
)