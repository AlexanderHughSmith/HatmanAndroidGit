package com.zanhsmitty.hatman.di

import android.content.Context
import com.zanhsmitty.hatman.data.HatmanDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.junit.Assert.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModuleTest {
    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): HatmanDataStore {
        return HatmanDataStore(context)
    }
}