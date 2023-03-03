package com.battousai.gamelist.models

data class GameListResponseModel(
    val count: Long,
    val next: String,
    val previous: String,
    val results: List<GameModel>
)