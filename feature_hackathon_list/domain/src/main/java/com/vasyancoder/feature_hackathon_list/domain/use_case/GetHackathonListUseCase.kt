package com.vasyancoder.feature_hackathon_list.domain.use_case

import androidx.lifecycle.LiveData
import com.vasyancoder.feature_hackathon_list.domain.HackathonRepository
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon

class GetHackathonListUseCase(
    private val repository: HackathonRepository
) {

    operator fun invoke(tag: String = ""): LiveData<List<Hackathon>> {
        return repository.getHackathonList(tag)
    }
}