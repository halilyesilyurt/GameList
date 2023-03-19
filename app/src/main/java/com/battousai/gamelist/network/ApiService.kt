package com.battousai.gamelist.network

import com.battousai.gamelist.models.CategoryListGameResponseModel
import com.battousai.gamelist.models.CategoryListResponseModel
import com.battousai.gamelist.models.GameListResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGameList(@Query("page_size") pageSize: Int): GameListResponseModel

    @GET("genres")
    suspend fun getCategoryList(): CategoryListResponseModel

    @GET("genres/{id}")
    suspend fun getCategoryListGames(@Path("id") categoryid:Int): CategoryListGameResponseModel
}