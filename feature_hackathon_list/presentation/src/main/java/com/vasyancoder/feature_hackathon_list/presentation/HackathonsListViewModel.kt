package com.vasyancoder.feature_hackathon_list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vasyancoder.data.HackathonRepositoryImpl
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon
import com.vasyancoder.feature_hackathon_list.domain.use_case.GetHackathonListUseCase
import com.vasyancoder.feature_hackathon_list.domain.use_case.GetTagListUseCase

class HackathonsListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HackathonRepositoryImpl(application)

    private val getTagListUseCase = GetTagListUseCase(repository)
    private val getHackathonListUseCase = GetHackathonListUseCase(repository)

    val hackathonList: LiveData<List<Hackathon>> = getHackathonListUseCase("")
}