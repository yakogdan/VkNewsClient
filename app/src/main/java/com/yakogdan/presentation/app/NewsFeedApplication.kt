package com.yakogdan.presentation.app

import android.app.Application
import com.yakogdan.di.ApplicationComponent
import com.yakogdan.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this
        )
    }
}