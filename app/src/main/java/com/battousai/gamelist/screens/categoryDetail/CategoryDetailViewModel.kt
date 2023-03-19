package com.battousai.gamelist.screens.categoryDetail

import androidx.lifecycle.viewModelScope
import com.battousai.gamelist.base.BaseViewModel
import com.battousai.gamelist.common.CommonLiveEvent
import com.battousai.gamelist.models.CategoryListGameResponseModel
import com.battousai.gamelist.repository.RemoteRepository
import kotlinx.coroutines.launch

class CategoryDetailViewModel:BaseViewModel() {
    val eventFetchData = CommonLiveEvent<CategoryListGameResponseModel>()
    val eventShowProgress = CommonLiveEvent<Boolean>()

    fun getData(categoryid:Int) {
        viewModelScope.launch {
            eventShowProgress.postValue(true)
            val response = RemoteRepository.SHARED.getCategoryListGame(categoryid)
            eventShowProgress.postValue(false)
            eventFetchData.postValue(response)
        }
    }
}