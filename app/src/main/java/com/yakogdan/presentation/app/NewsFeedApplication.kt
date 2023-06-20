package com.yakogdan.presentation.app

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yakogdan.di.ApplicationComponent
import com.yakogdan.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            this
        )
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("RECOMPOSITION", "getApplicationComponent")
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}