package com.vasyancoder.data.database.model

import androidx.room.PrimaryKey

data class HackathonModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val organization: String,
    val dates: String,
    val status: String,
)