package com.vasyancoder.feature_hackathon_list.domain

import androidx.lifecycle.LiveData
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon
import com.vasyancoder.feature_hackathon_list.domain.entity.Tag

interface HackathonRepository {

    fun getTagList(): List<Tag>

    fun getHackathonList(tag: String = ""): LiveData<List<Hackathon>>
    suspend fun addHackathonItemUseCase(hackathon: Hackathon)
}