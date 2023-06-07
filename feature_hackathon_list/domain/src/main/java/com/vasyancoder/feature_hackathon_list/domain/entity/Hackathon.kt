package com.vasyancoder.feature_hackathon_list.domain.entity

data class Hackathon(
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
