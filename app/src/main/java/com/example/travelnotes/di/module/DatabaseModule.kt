package com.example.travelnotes.di.module

import android.content.Context
import com.example.travelnotes.data.database.AppDatabase
import com.example.travelnotes.data.database.AppRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase = AppRoomDatabase.buildDatabase(context)

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase) = appDatabase.noteDao()
}