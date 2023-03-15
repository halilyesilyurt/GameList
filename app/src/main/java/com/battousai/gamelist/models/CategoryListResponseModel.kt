package com.battousai.gamelist.models

import com.google.gson.annotations.SerializedName


data class CategoryListResponseModel (

	@SerializedName("count")
	val count : Int,
	@SerializedName("next")
	val next : String,
	@SerializedName("previous")
	val previous : String,
	@SerializedName("results")
	val results : List<CategoryModel>
)