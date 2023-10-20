package com.battousai.gamelist.screens.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.battousai.gamelist.R
import com.battousai.gamelist.models.GameModel

class GameListAdapter(
    private var addOrRemoveFavoriteListener: AddOrRemoveFavoriteListener
) : RecyclerView.Adapter<GameListViewHolder>() {

    private var gameList = mutableListOf<GameModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        return GameListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bind(gameList[position], addOrRemoveFavoriteListener)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    fun updateList(itemList: List<GameModel>) {
        gameList.clear()
        gameList = itemList as MutableList<GameModel>
        this.notifyDataSetChanged()
    }
}