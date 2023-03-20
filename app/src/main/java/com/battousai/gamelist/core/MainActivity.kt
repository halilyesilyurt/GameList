package com.battousai.gamelist.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.battousai.gamelist.R
import com.battousai.gamelist.managers.NavigationManager
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.repository.LocalRepository

class MainActivity : AppCompatActivity(), AddOrRemoveFavoriteListener {

    private var gameList = ArrayList<GameModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.shared.launch(this@MainActivity)


        val favoriteList = LocalRepository(this).favoriteGameList
        if (favoriteList != null) {
            for (i in favoriteList) {
                for (x in gameList){
                    if (x.gameId == i.gameId){
                        x.isFavorite = true
                    }


                }
            }
        }
    }

     override fun onAddOrRemoveFavorite(game: GameModel, isAdd: Boolean) {
        if (isAdd) {
            LocalRepository(this).add(game)
        } else {
            LocalRepository(this).remove(game)
        }

    }






}