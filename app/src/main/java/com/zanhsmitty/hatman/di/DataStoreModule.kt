package com.zanhsmitty.hatman.di

import android.content.Context
import androidx.room.Room
import com.zanhsmitty.hatman.data.HatmanDataStore
import com.zanhsmitty.hatman.data.HatmanDatabase
import com.zanhsmitty.hatman.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ):  HatmanDataStore {
        return HatmanDataStore(context)
    }
}