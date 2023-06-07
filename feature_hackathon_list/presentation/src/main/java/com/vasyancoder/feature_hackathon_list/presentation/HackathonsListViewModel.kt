package com.vasyancoder.feature_hackathon_list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vasyancoder.data.HackathonRepositoryImpl
import com.vasyancoder.feature_hackathon_list.domain.entity.Hackathon
import com.vasyancoder.feature_hackathon_list.domain.use_case.GetHackathonListUseCase
import com.vasyancoder.feature_hackathon_list.domain.use_case.GetTagListUseCase

class HackathonsListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = HackathonRepositoryImpl(application)

    private val getTagListUseCase = GetTagListUseCase(repository)
    private val getHackathonListUseCase = GetHackathonListUseCase(repository)

    private val _hackathonList = MutableLiveData<List<Hackathon>>()
    val hackathonList: LiveData<List<Hackathon>> = _hackathonList

    val tagsList = getTagListUseCase()

    init {
        getHackathonListUseCase().observeForever { hackathons ->
            _hackathonList.value = hackathons
        }
    }

    fun updateHackathons(tag: String) {
        getHackathonListUseCase(tag).observeForever { hackathons ->
            _hackathonList.value = hackathons
        }
    }
}