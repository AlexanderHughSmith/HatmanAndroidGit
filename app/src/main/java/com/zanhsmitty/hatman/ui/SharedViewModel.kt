package com.zanhsmitty.hatman.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zanhsmitty.hatman.data.HatmanDataStore
import com.zanhsmitty.hatman.data.HatmanRepository
import com.zanhsmitty.hatman.data.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.properties.Delegates
import kotlin.random.Random

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: HatmanRepository,
    private val dataStore: HatmanDataStore
) : ViewModel() {

    val die1: MutableState<Int> = mutableStateOf(1)
    val die2: MutableState<Int> = mutableStateOf(2)
    val isDieShown: MutableState<Boolean> = mutableStateOf(false)
    val displayText: MutableState<String> = mutableStateOf("Roll to start!")
    val rotateAngle: MutableState<Float> = mutableStateOf(0f)
    val comingFromPlayingScreen: MutableState<Boolean> = mutableStateOf(false)
    val gameAlreadySetUp: MutableState<Boolean> = mutableStateOf(false)
    val useDynamicColors: MutableState<Boolean> = mutableStateOf(true)
    val darkTheme: MutableState<Boolean?> = mutableStateOf(null)

    private var random = Random(System.currentTimeMillis())

    private var hatmanIndex by Delegates.notNull<Int>()
    private var currentIndex by Delegates.notNull<Int>()
    private var aheadIndex by Delegates.notNull<Int>()
    private var behindIndex by Delegates.notNull<Int>()


    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players


    suspend fun addPlayers(players: List<String>) {
        val playerList = mutableListOf<Player>()
        players.forEach {
            playerList += Player(
                id = 0,
                name = it,
                score = 0,
                isHatman = false,
                isRolling = false
            )
        }
        repository.addPlayers(playerList)
        while(_players.value.isEmpty()){
            delay(100)
            _players.value = repository.getAllPlayers.first()
        }
        setRoles()
        saveChanges()
        displayText.value = "Roll to start!"
        die1.value = 1
        die2.value = 2
        isDieShown.value = true
    }

    suspend fun deleteAllPlayers() {
        repository.deleteAllPlayers()
        _players.value = listOf()
    }

    fun setupFromMain() {
        viewModelScope.launch {
            //darkTheme.value = dataStore.getDarkTheme.first()
            darkTheme.value = false
            //useDynamicColors.value = dataStore.getDynamicColors.first()
            useDynamicColors.value = true
        }
    }

    suspend fun setupFromSplash(){
        _players.value = repository.getAllPlayers.first()
        if(players.value.isNotEmpty()){
            setRoles()
            saveChanges()
            setupDataStore()
        }
    }

    private fun setRoles(){
        currentIndex = players.value.indexOfFirst{ player ->
            player.isRolling
        }
        if(currentIndex == -1) {
            currentIndex = random.nextInt(0, players.value.size)
        }

        hatmanIndex = players.value.indexOfFirst{ player ->
            player.isHatman
        }

        if(hatmanIndex == -1) {
            hatmanIndex = random.nextInt(0, players.value.size)
        }

        while(currentIndex == hatmanIndex){
            hatmanIndex = random.nextInt(0, players.value.size)
        }

        players.value[currentIndex].isRolling = true
        players.value[hatmanIndex].isHatman = true

        aheadIndex = (currentIndex + 1) % players.value.size
        behindIndex = if(currentIndex > 0)
            (currentIndex - 1) % players.value.size
        else
            players.value.size - 1
    }

    suspend fun handleRoll(){
        displayText.value = "Rolling..."
        var speed = 40L
        val random = Random(System.currentTimeMillis())
        for (i in 1..10) {
            die1.value = random.nextInt(1, 7)
            die2.value = random.nextInt(1, 7)
            rotateAngle.value += 36f
            delay(speed)
            speed += 10
        }
        displayText.value = handleDiceRoll(die1.value, die2.value)
        dataStore.saveDisplayText(displayText.value)
        dataStore.saveDieOne(die1.value)
        dataStore.saveDieTwo(die2.value)
    }

    private fun handleDiceRoll(die1: Int, die2:Int): String {
        if(die1+die2 == 3){
            handleNewHatman()
            return "${players.value[hatmanIndex].name} is now the Hatman!\n${players.value[currentIndex].name} is now rolling!"
        }
        var message = ""
        if(die1 == 3 && die2 == 3){
            players.value[hatmanIndex].score += 2
            message = "Two 3s, Hatman ${players.value[hatmanIndex].name} has to drink 2x!\n"
        }
        else if(die1 == 3 || die2 == 3){
            players.value[hatmanIndex].score += 1
            message = "3, Hatman ${players.value[hatmanIndex].name} has to drink!\n"
        }

        if(die1 == die2){
            var randomIndex = random.nextInt(0, players.value.size)
            while(randomIndex == currentIndex){
                randomIndex = random.nextInt(0, players.value.size)
            }
            players.value[randomIndex].score += die1
            message += "Doubles, ${players.value[randomIndex].name} has to drink ${die1}x!\n"
        }

        if(die1 + die2 == 7){
            players.value[aheadIndex].score += 1
            message += "7 ahead, ${players.value[aheadIndex].name} has to drink!\n"
        }
        else if(die1 + die2 == 9){
            players.value[behindIndex].score += 1
            message += "9 behind, ${players.value[behindIndex].name} has to drink!\n"
        }
        else if(die1 + die2 == 10){
            players.value.forEach{
                it.score += 1
            }
            message += "10 Community, Everyone has to drink!\n"
        }

        if(message == ""){
            handleNewTurn()
            message = "Nothing, ${players.value[currentIndex].name} is now rolling!\n"
        }
        return message
    }

    private fun handleNewTurn(){
        if(players.value[aheadIndex].isHatman){
            aheadIndex = (aheadIndex + 1) % players.value.size
        }
        players.value[currentIndex].isRolling = false
        currentIndex = aheadIndex
        players.value[currentIndex].isRolling = true
        aheadIndex = (currentIndex + 1) % players.value.size
        behindIndex = if(currentIndex > 0)
            (currentIndex - 1) % players.value.size
        else
            players.value.size - 1
    }

    private fun handleNewHatman(){
        players.value[hatmanIndex].isHatman = false
        players.value[currentIndex].isRolling = false
        hatmanIndex = currentIndex

        players.value[hatmanIndex].isHatman = true
        currentIndex = aheadIndex
        players.value[currentIndex].isRolling = true

        aheadIndex = (currentIndex + 1) % players.value.size
        behindIndex = if(currentIndex > 0)
            (currentIndex - 1) % players.value.size
        else
            players.value.size - 1
    }

    private suspend fun saveChanges(){
        repository.updateAllPlayers(players.value)
    }

    suspend fun updatePlayers(){
        repository.updateAllPlayers(players.value)
    }

    fun updateDarkTheme(){
        viewModelScope.launch {
            dataStore.saveDarkTheme(darkTheme.value)
        }
    }

    fun updateDynamicColors(){
        viewModelScope.launch {
            dataStore.saveDynamicColors(useDynamicColors.value)
        }
    }

    private suspend fun setupDataStore() {
        with(dataStore) {
            die1.value = getDieOne.first()
            displayText.value = getDisplayText.first()
            die2.value = getDieTwo.first()
        }
        isDieShown.value = true
    }
    suspend fun handleNewGameDataStore(){
        dataStore.handleNewGameDataStore()
    }
}