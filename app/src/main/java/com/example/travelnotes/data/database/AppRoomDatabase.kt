package com.example.travelnotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelnotes.BuildConfig
import com.example.travelnotes.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase(), AppDatabase {

    companion object {
        private const val DATABASE_NAME = "database.db"

        fun buildDatabase(context: Context): AppRoomDatabase =
            Room.databaseBuilder(context, AppRoomDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}