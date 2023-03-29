package com.battousai.gamelist.screens.categoryDetail

import androidx.lifecycle.viewModelScope
import com.battousai.gamelist.base.BaseViewModel
import com.battousai.gamelist.common.CommonLiveEvent
import com.battousai.gamelist.models.CategoryDetailResponseModel
import com.battousai.gamelist.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryDetailViewModel:BaseViewModel() {
    val eventFetchData = CommonLiveEvent<CategoryDetailResponseModel>()
    val eventShowProgress = CommonLiveEvent<Boolean>()

    fun getData(categoryid:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            eventShowProgress.postValue(true)
            val response = RemoteRepository.SHARED.getCategoryDetail(categoryid)
            eventShowProgress.postValue(false)
            eventFetchData.postValue(response)
        }
    }
}