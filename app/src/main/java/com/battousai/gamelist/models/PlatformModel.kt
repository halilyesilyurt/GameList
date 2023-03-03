package com.battousai.gamelist.models

import com.google.gson.annotations.SerializedName

data class PlatformModel(
    val platform: PlatformDetailModel,
    @SerializedName("released_at")
    val releasedAt: String,
    val requirements: RequirementsModel,
)
