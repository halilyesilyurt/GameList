package com.battousai.gamelist.screens.favorite


import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.battousai.gamelist.base.BaseViewModel
import com.battousai.gamelist.common.CommonLiveEvent
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.repository.LocalRepository
import com.battousai.gamelist.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    val eventFetchData = CommonLiveEvent<List<GameModel>?>()
    val eventShowProgress = CommonLiveEvent<Boolean>()
    private val localRepository = LocalRepository(application)


    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            eventShowProgress.postValue(true)
            localRepository.favoriteGameList?.let { favoriteList ->
                val response = RemoteRepository.SHARED.getGameList()
                val filteredList = filterList(response.results, favoriteList)
                eventShowProgress.postValue(false)
                eventFetchData.postValue(filteredList)
            } ?: run {
                eventShowProgress.postValue(false)
                eventFetchData.postValue(null)
            }
        }
    }

    private fun filterList(gameList: List<GameModel>, favorites: List<GameModel>): List<GameModel> {
        for (favoriteItem in favorites) {
            for (gameItem in gameList) {
                if (favoriteItem.id == gameItem.id) {
                    favoriteItem.isFavorite = true
                }
            }
        }
        return favorites
    }
}