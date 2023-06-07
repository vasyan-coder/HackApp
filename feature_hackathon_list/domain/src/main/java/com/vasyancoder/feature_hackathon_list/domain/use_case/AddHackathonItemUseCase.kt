package com.vasyancoder.feature_hackathon_list.domain.use_case

import com.vasyancoder.feature_hackathon_list.domain.HackathonRepository
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon

class AddHackathonItemUseCase(
   private val repository: HackathonRepository
) {

    suspend operator fun invoke(hackathon: Hackathon) {
        repository.addHackathonItemUseCase(hackathon)
    }
}