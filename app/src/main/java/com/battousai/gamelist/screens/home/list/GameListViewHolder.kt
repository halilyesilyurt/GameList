package com.battousai.gamelist.screens.home.list

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.battousai.gamelist.R
import com.battousai.gamelist.models.GameModel
import com.bumptech.glide.Glide

class GameListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvName: AppCompatTextView = itemView.findViewById(R.id.tvName)
    private val imgPoster: AppCompatImageView = itemView.findViewById(R.id.image)

    fun bind(gameModel: GameModel) {
        tvName.text = gameModel.name
        Glide
            .with(itemView.context)
            .load(gameModel.backgroundImage)
            .centerCrop()
            .into(imgPoster);
    }

}