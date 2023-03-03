package com.battousai.gamelist.screens.home

import androidx.lifecycle.viewModelScope
import com.battousai.gamelist.base.BaseViewModel
import com.battousai.gamelist.common.CommonLiveEvent
import com.battousai.gamelist.models.GameListResponseModel
import com.battousai.gamelist.repository.RemoteRepository
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    val eventFetchData = CommonLiveEvent<GameListResponseModel>()
    val eventShowProgress = CommonLiveEvent<Boolean>()

    fun getData() {
        viewModelScope.launch {
            eventShowProgress.postValue(true)
            val response = RemoteRepository.SHARED.getGameList()
            eventShowProgress.postValue(false)
            eventFetchData.postValue(response)
        }
    }
}