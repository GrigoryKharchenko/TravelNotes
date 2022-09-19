package com.example.travelnotes.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travelnotes.R
import com.example.travelnotes.presentation.screen.note.list.NotesFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.container,
                    NotesFragment.newInstance()
                )
                .commit()
    }
}
