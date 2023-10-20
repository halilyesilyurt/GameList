package com.battousai.gamelist.screens.home

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.battousai.gamelist.base.BaseViewModel
import com.battousai.gamelist.common.CommonLiveEvent
import com.battousai.gamelist.models.GameListResponseModel
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.repository.LocalRepository
import com.battousai.gamelist.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val eventFetchData = CommonLiveEvent<List<GameModel>>()
    val eventShowProgress = CommonLiveEvent<Boolean>()

    private val localRepository = LocalRepository(application)
    private var favoriteList: List<GameModel>? = null


    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            eventShowProgress.postValue(true)
            favoriteList = localRepository.favoriteGameList
            val response = RemoteRepository.SHARED.getGameList()
            val filteredList = filterList(response.results)
            eventShowProgress.postValue(false)
            eventFetchData.postValue(filteredList)
        }
    }

    private fun filterList(gameList: List<GameModel>): List<GameModel> {
        if (favoriteList != null) {
            for (favoriteItem in favoriteList!!) {
                for (gameItem in gameList) {
                    if (favoriteItem.id == gameItem.id) {
                        gameItem.isFavorite = true
                    }
                }
            }
        }
        return gameList
    }

    fun addOrRemove(game: GameModel, isAdd: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isAdd) {
                game.isFavorite = true
                localRepository.add(game)
            } else
                localRepository.remove(game)
        }
    }
}