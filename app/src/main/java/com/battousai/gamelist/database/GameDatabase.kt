package com.battousai.gamelist.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.battousai.gamelist.models.GameModel
import com.battousai.gamelist.utils.EsrbTypeConverter
import com.battousai.gamelist.utils.PlatformListTypeConverter

@Database(entities = [GameModel::class], version = 1, exportSchema = false)
@TypeConverters(EsrbTypeConverter::class,PlatformListTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao?

    companion object {
        private val LOG_TAG = GameDatabase::class.java.simpleName
        private val LOCK = Any()
        private const val DATABASE_NAME = "games"
        private var sInstance: GameDatabase? = null
        fun getInstance(context: Context): GameDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    Log.d(LOG_TAG, "Creating new database instance")
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        GameDatabase::class.java, DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            Log.d(LOG_TAG, "Getting the database instance")
            return sInstance
        }
    }
}