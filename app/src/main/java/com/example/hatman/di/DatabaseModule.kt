package com.example.hatman.di

import android.content.Context
import androidx.room.Room
import com.example.hatman.data.HatmanDatabase
import com.example.hatman.data.HatmanRepository
import com.example.hatman.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): HatmanDatabase {
        return Room.databaseBuilder(
            context,
            HatmanDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: HatmanDatabase) = database.hatmanDao()
}