package com.battousai.gamelist.models

import com.google.gson.annotations.SerializedName


data class CategoryDetailResponseModel (
	@SerializedName("id")
	val id : Int,
	@SerializedName("name")
	val name : String,
	@SerializedName("slug")
	val slug : String,
	@SerializedName("games_count")
	val games_count : Int,
	@SerializedName("image_background")
	val image_background : String,
	@SerializedName("description")
	val description : String
)