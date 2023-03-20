package com.battousai.gamelist.utils

import androidx.room.TypeConverter
import com.battousai.gamelist.models.PlatformModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PlatformListTypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToMap(value: String): List<PlatformModel>? {
        return Gson().fromJson(value, object : TypeToken<List<PlatformModel>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun mapToString(value: List<PlatformModel>?): String {
        return if (value == null) "" else Gson().toJson(value)
    }

}