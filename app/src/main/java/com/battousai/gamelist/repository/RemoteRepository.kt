package com.battousai.gamelist.repository

import com.battousai.gamelist.models.CategoryListResponseModel
import com.battousai.gamelist.models.GameListResponseModel
import com.battousai.gamelist.network.NetworkHelper

class RemoteRepository {

    suspend fun getGameList(): GameListResponseModel {
        return NetworkHelper.service.getGameList(30)
    }

    suspend fun getCategoryList(): CategoryListResponseModel {
        return NetworkHelper.service.getCategoryList()
    }


    companion object {

        val SHARED: RemoteRepository = getInstance()

        @Volatile
        private var instance: RemoteRepository? = null

        private fun init(): RemoteRepository = instance ?: synchronized(this) {
            instance ?: RemoteRepository().also { instance = it }
        }

        private fun getInstance(): RemoteRepository =
            instance ?: init()
    }
}