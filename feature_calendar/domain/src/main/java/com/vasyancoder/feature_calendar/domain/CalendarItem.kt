package com.vasyancoder.feature_calendar.domain

data class CalendarItem(
    val name: String,
    val organization: String,
    val dates: String,
    val status: String,
    val id: Int = UNDEFINED_ID
) {
    companion object {

        const val UNDEFINED_ID = 0
    }
}