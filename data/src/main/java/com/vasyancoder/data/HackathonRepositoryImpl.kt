package com.vasyancoder.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vasyancoder.data.database.HackAppDatabase
import com.vasyancoder.data.database.mapper.HackathonMapper
import com.vasyancoder.feature_hackathon_list.domain.HackathonRepository
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon
import com.vasyancoder.feature_hackathon_list.domain.entity.Tag

class HackathonRepositoryImpl(
    private val context: Context
) : HackathonRepository {

    private val db = HackAppDatabase.getDatabase(context)
    private val hackathonDao = db.hackathonDao()

    override suspend fun getTagList() = listOf<Tag>(
        Tag(
            name = "Online",
            status = false
        ),
        Tag(
            name = "Offline",
            status = false
        ),
    )

    override fun getHackathonList(tag: String): LiveData<List<Hackathon>> {
        return if (tag == "") {
            Transformations.map(
                hackathonDao.getHackathonList()
            ) {
                HackathonMapper.mapListDbModelToListEntity(it)
            }
        } else {
            Transformations.map(
                hackathonDao.getFilterHackathonList(tag)
            ) {
                HackathonMapper.mapListDbModelToListEntity(it)
            }
        }
    }
}