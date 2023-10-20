package com.battousai.gamelist.database


import androidx.room.*
import com.battousai.gamelist.models.GameModel

@Dao
interface GamesDao {

    @Query("SELECT * FROM dbGame")
    fun getAllFavoriteGames(): List<GameModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(favMovie: GameModel)

    @Query("DELETE FROM dbGame WHERE id = :gameId")
    fun removeFavorite(gameId: Long)
}