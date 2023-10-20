package com.battousai.gamelist.screens.favorite

import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.battousai.gamelist.R
import com.battousai.gamelist.models.GameModel
import com.bumptech.glide.Glide

class FavoriteListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val tvName: AppCompatTextView = itemView.findViewById(R.id.tvName)
    private val imgPoster: AppCompatImageView = itemView.findViewById(R.id.image)
    private val btnFavorite: AppCompatCheckBox = itemView.findViewById(R.id.btnFav)

    fun bind(gameModel: GameModel) {
        btnFavorite.isChecked = gameModel.isFavorite
        tvName.text = gameModel.name
        Glide
            .with(itemView.context)
            .load(gameModel.backgroundImage)
            .centerCrop()
            .into(imgPoster);
    }
}