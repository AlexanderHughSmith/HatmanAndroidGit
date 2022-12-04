package com.example.hatman.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hatman.data.model.Player

@Database(entities = [Player::class], version = 1)
abstract class HatmanDatabase : RoomDatabase() {

    abstract fun hatmanDao(): HatmanDao

}