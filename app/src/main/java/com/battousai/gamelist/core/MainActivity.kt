package com.battousai.gamelist.core


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.battousai.gamelist.R
import com.battousai.gamelist.managers.NavigationManager
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.repository.LocalRepository
import com.battousai.gamelist.screens.home.list.AddOrRemoveFavoriteListener


class MainActivity : AppCompatActivity(),AddOrRemoveFavoriteListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.shared.launch(this@MainActivity)



    }


    override fun onAddOrRemoveFavorite(game: GameModel, isAdd: Boolean) {
        if (isAdd) {
            LocalRepository(this).add(game)
        } else {
            LocalRepository(this).remove(game)
        }

    }







}