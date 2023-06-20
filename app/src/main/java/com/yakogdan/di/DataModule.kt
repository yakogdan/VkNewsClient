package com.yakogdan.di

import android.content.Context
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.yakogdan.data.network.ApiFactory
import com.yakogdan.data.network.ApiService
import com.yakogdan.data.repository.NewsFeedRepositoryImpl
import com.yakogdan.domain.repository.NewsFeedRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(context: Context): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}