package com.zanhsmitty.hatman.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zanhsmitty.hatman.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var score: Int,
    var isHatman: Boolean,
    var isRolling: Boolean,
)
