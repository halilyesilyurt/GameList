package com.battousai.gamelist.screens.home

import androidx.lifecycle.viewModelScope
import com.battousai.gamelist.base.BaseViewModel
import com.battousai.gamelist.common.CommonLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    val eventFetchData = CommonLiveEvent<String>()
    val eventShowProgress = CommonLiveEvent<Boolean>()

    fun getData() {

        viewModelScope.launch {
            eventShowProgress.postValue(true)
            delay(3000)
            eventShowProgress.postValue(false)
            eventFetchData.postValue("Data web servisten geldi")
        }
    }
}