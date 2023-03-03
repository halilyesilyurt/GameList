package com.battousai.gamelist.screens.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.battousai.gamelist.R
import com.battousai.gamelist.models.GameModel

class GameListAdapter(private val gameList: List<GameModel>) :
    RecyclerView.Adapter<GameListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        return GameListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int {
        return gameList.size
    }
}