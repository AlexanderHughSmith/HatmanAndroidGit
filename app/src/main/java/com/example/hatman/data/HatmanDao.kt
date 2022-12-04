package com.example.hatman.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hatman.data.model.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface HatmanDao {
    @Query("SELECT * FROM player_table ORDER BY id ASC")
    fun getAllPlayers(): Flow<List<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayers(players: List<Player>)

    @Query("DELETE FROM player_table")
    suspend fun deleteAllPlayers()

    suspend fun updateAllPlayers(players: List<Player>) {
        players.forEach {
            updatePlayer(it)
        }
    }

    @Update
    suspend fun updatePlayer(player: Player)
}