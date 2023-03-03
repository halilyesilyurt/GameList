package com.battousai.gamelist.models

import com.google.gson.annotations.SerializedName

data class GameModel(
    val id: Long,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    @SerializedName("background_image")
    val backgroundImage: String,
    val rating: Double,
    @SerializedName("rating_top")
    val ratingTop: Long,
    @SerializedName("ratings_count")
    val ratingsCount: Long,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: String,
    val added: Long,
    @SerializedName("added_by_status")
    val addedByStatus: Map<String, Any>,
    val metacritic: Long,
    val playtime: Long,
    @SerializedName("suggestions_count")
    val suggestionsCount: Long,
    val updated: String,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRatingModel,
    val platforms: List<PlatformModel>
)
