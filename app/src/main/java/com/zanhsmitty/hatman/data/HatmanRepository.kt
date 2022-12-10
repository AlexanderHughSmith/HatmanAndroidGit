package com.zanhsmitty.hatman.data

import com.zanhsmitty.hatman.data.model.Player
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class HatmanRepository @Inject constructor(private val HatmanDao: HatmanDao) {

    val getAllPlayers: Flow<List<Player>> = HatmanDao.getAllPlayers()

    suspend fun addPlayers(players: List<Player>) {
        HatmanDao.addPlayers(players = players)
    }
    suspend fun deleteAllPlayers() {
        HatmanDao.deleteAllPlayers()
    }

    suspend fun updateAllPlayers(players: List<Player>) {
        HatmanDao.updateAllPlayers(players = players)
    }
}