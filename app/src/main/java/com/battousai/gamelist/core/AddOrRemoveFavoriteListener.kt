package com.battousai.gamelist.core

import com.battousai.gamelist.models.GameModel

interface AddOrRemoveFavoriteListener {
    fun onAddOrRemoveFavorite(game: GameModel, isAdd: Boolean)
}