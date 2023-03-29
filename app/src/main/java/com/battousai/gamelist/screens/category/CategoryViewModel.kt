package com.battousai.gamelist.screens.category

import androidx.lifecycle.viewModelScope
import com.battousai.gamelist.base.BaseViewModel
import com.battousai.gamelist.common.CommonLiveEvent
import com.battousai.gamelist.models.CategoryListResponseModel
import com.battousai.gamelist.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel : BaseViewModel() {
    val eventFetchData = CommonLiveEvent<CategoryListResponseModel>()
    val eventShowProgress = CommonLiveEvent<Boolean>()

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            eventShowProgress.postValue(true)
            val response = RemoteRepository.SHARED.getCategoryList()
            eventShowProgress.postValue(false)
            eventFetchData.postValue(response)
        }
    }
}