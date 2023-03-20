package com.battousai.gamelist.utils

import androidx.room.TypeConverter
import com.battousai.gamelist.models.EsrbRatingModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object EsrbTypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMap(value: String): EsrbRatingModel? {
        return Gson().fromJson(value, object : TypeToken<EsrbRatingModel>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun mapToString(value: EsrbRatingModel?): String {
        return if (value == null) "" else Gson().toJson(value)
    }

}