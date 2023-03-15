package com.battousai.gamelist.screens.category.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.battousai.gamelist.R
import com.battousai.gamelist.models.CategoryModel

class CategoryListAdapter(private val categoryList:List<CategoryModel>):
    RecyclerView.Adapter<CategoryListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_item_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size

    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.bind(categoryList[position])

    }
}