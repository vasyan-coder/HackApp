package com.vasyancoder.data.database.mapper

import com.vasyancoder.data.database.model.HackathonModel
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon

object HackathonMapper {

    fun mapEntityToDbModel(hackathon: Hackathon) = HackathonModel(
        id = hackathon.id,
        name = hackathon.name,
        organization = hackathon.organization,
        dates = hackathon.dates,
        status = hackathon.status
    )

    fun mapDbModelToEntity(hackathonModel: HackathonModel) = Hackathon(
        id = hackathonModel.id,
        name = hackathonModel.name,
        organization = hackathonModel.organization,
        dates = hackathonModel.dates,
        status = hackathonModel.status
    )

    fun mapListDbModelToListEntity(list: List<HackathonModel>) = list.map {
        mapDbModelToEntity(it)
    }
}