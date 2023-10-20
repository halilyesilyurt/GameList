package com.battousai.gamelist.repository

import android.content.Context
import com.battousai.gamelist.database.GameDatabase
import com.battousai.gamelist.models.GameModel

class LocalRepository(context: Context) {

    private val gameDB = GameDatabase.getInstance(context)

    val favoriteGameList: List<GameModel>? = gameDB?.gamesDao()?.getAllFavoriteGames()

    fun add(game: GameModel) {
        gameDB?.gamesDao()?.addFavorite(game)
    }

    fun remove(game: GameModel) {
        gameDB?.gamesDao()?.removeFavorite(game.id)
    }

    fun getFavoriteList(game: GameModel) {
        gameDB?.gamesDao()?.getAllFavoriteGames()
    }

}