package com.example.hatman.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hatman.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var score: Int,
    var isHatman: Boolean,
    var isRolling: Boolean,
)

    /*constructor(name: String) {
        this.name = name
    }

    fun addPoints(points: Int) {
        score += points
    }

    fun provideFakeData(): List<Player>{
        return listOf(
            Player("Player 0"),
            Player("Player 1").apply {
                isHatman = true
            },
            Player("Player 2"),
            Player("Player 3").apply {
                score = 0
                isRolling = true
             },
        )
    }*/
