package com.battousai.gamelist.network

import com.battousai.gamelist.models.GameListResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGameList(@Query("page_size") pageSize: Int): GameListResponseModel
}