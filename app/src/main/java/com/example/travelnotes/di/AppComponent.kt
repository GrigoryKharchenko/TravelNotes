package com.example.travelnotes.di

import android.content.Context
import com.example.travelnotes.TravelApp
import com.example.travelnotes.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<TravelApp> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
