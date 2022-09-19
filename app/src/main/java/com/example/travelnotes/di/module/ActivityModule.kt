package com.example.travelnotes.di.module

import com.example.travelnotes.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun provideMainActivity():MainActivity
}