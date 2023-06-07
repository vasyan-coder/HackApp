package com.vasyancoder.feature_hackathon_list.domain.use_case

import com.vasyancoder.feature_hackathon_list.domain.HackathonRepository
import com.vasyancoder.feature_hackathon_list.domain.entity.Tag

class GetTagListUseCase(
    private val repository: HackathonRepository
) {

    suspend operator fun invoke(): List<Tag> {
        return repository.getTagList()
    }
}