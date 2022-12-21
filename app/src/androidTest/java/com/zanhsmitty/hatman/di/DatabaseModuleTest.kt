package com.zanhsmitty.hatman.di

import android.content.Context
import androidx.room.Room
import com.zanhsmitty.hatman.data.HatmanDao
import com.zanhsmitty.hatman.data.HatmanDatabase
import com.zanhsmitty.hatman.data.HatmanRepository
import com.zanhsmitty.hatman.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.junit.Assert.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModuleTest {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): HatmanDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            HatmanDatabase::class.java,
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: HatmanDatabase) = database.hatmanDao()

    @Singleton
    @Provides
    fun provideHatmanRepository(dao: HatmanDao) = HatmanRepository(dao)
}