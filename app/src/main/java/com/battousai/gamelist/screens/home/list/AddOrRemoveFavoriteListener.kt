package com.battousai.gamelist.screens.home.list

import com.battousai.gamelist.models.GameModel

interface AddOrRemoveFavoriteListener {
    fun onAddOrRemoveFavorite(game: GameModel, isAdd: Boolean)
}