package com.battousai.gamelist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.battousai.gamelist.utils.EsrbTypeConverter
import com.battousai.gamelist.utils.PlatformListTypeConverter
import com.google.gson.annotations.SerializedName


@Entity(tableName = "dbGame")
data class GameModel(
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "tba")
    val tba: Boolean,
    @ColumnInfo(name = "IsFavorite")
    var isFavorite: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    var gameId: Int = 0,
    @SerializedName("background_image")
    @ColumnInfo(name = "backgroundImage")
    val backgroundImage: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @SerializedName("rating_top")
    @ColumnInfo(name = "ratingTop")
    val ratingTop: Long,
    @SerializedName("ratings_count")
    @ColumnInfo(name = "ratingsCount")
    val ratingsCount: Long,
    @SerializedName("reviews_text_count")
    @ColumnInfo(name = "reviewsTextCount")
    val reviewsTextCount: String,
    @ColumnInfo(name = "added")
    val added: Long,
    @ColumnInfo(name = "playtime")
    val playtime: Long,
    @SerializedName("suggestions_count")
    @ColumnInfo(name = "suggestionsCount")
    val suggestionsCount: Long,
    @ColumnInfo(name = "updated")
    val updated: String,
    @SerializedName("esrb_rating")
    @ColumnInfo(name = "esrbRating")
    @TypeConverters(EsrbTypeConverter::class)
    val esrbRating: EsrbRatingModel?,
    @ColumnInfo(name = "platforms")
    @TypeConverters(PlatformListTypeConverter::class)
    val platforms: List<PlatformModel>?


)
