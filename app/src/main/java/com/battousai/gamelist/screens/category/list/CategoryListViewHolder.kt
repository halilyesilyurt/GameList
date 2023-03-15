package com.battousai.gamelist.screens.category.list

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.battousai.gamelist.R
import com.battousai.gamelist.models.CategoryModel
import com.bumptech.glide.Glide

class CategoryListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvName: AppCompatTextView = itemView.findViewById(R.id.categoryName)
    private val imgPoster: AppCompatImageView = itemView.findViewById(R.id.categoryImage)
    private val gameCount: AppCompatTextView = itemView.findViewById(R.id.countGame)


    @SuppressLint("SetTextI18n")
    fun bind(categoryModel: CategoryModel) {
        tvName.text = categoryModel.name
        gameCount.text= "${categoryModel.games_count} Game"
        Glide
            .with(itemView.context)
            .load(categoryModel.image_background)
            .centerCrop()
            .into(imgPoster)
    }

}