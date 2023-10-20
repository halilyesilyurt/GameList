package com.battousai.gamelist.screens.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.battousai.gamelist.R
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.screens.home.list.GameListViewHolder

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListViewHolder>() {

    private var favoriteList = mutableListOf<GameModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        return FavoriteListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateList(itemList: List<GameModel>?) {
        favoriteList.clear()
        favoriteList = itemList as MutableList<GameModel>
        this.notifyDataSetChanged()
    }


}
