package com.zanhsmitty.hatman.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HatmanDataStore(private val context: Context) {

    companion object{
        private val Context.dataStore by preferencesDataStore("HatmanDataStore")
        val DISPLAY_TEXT_KEY = stringPreferencesKey("display_text")
        val DIE_ONE_KEY = intPreferencesKey("die_one")
        val DIE_TWO_KEY = intPreferencesKey("die_two")
        val DYNAMIC_COLORS_KEY = booleanPreferencesKey("dynamic_colors")
        val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    }

    val getDisplayText: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DISPLAY_TEXT_KEY] ?: "Roll to start!"
        }

    suspend fun saveDisplayText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[DISPLAY_TEXT_KEY] = text
        }
    }

    val getDieOne: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[DIE_ONE_KEY] ?: 1
        }

    suspend fun saveDieOne(dieOne: Int) {
        context.dataStore.edit { preferences ->
            preferences[DIE_ONE_KEY] = dieOne
        }
    }

    val getDieTwo: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[DIE_TWO_KEY] ?: 2
        }

    suspend fun saveDieTwo(dieTwo: Int) {
        context.dataStore.edit { preferences ->
            preferences[DIE_TWO_KEY] = dieTwo
        }
    }

    val getDarkTheme: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_THEME_KEY]
        }

    suspend fun saveDarkTheme(darkTheme: Boolean?) {
        if(darkTheme != null){
            context.dataStore.edit { preferences ->
                preferences[DARK_THEME_KEY] = darkTheme
            }
        }
        else{
            context.dataStore.edit { preferences ->
                preferences.remove(DARK_THEME_KEY)
            }
        }
    }

    val getDynamicColors: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DYNAMIC_COLORS_KEY]?: false
        }

    suspend fun saveDynamicColors(dynamicColors: Boolean?) {
        if(dynamicColors != null){
            context.dataStore.edit { preferences ->
                preferences[DYNAMIC_COLORS_KEY] = dynamicColors
            }
        }
        else{
            context.dataStore.edit { preferences ->
                preferences.remove(DYNAMIC_COLORS_KEY)
            }
        }
    }

    suspend fun handleNewGameDataStore() {
        context.dataStore.edit { preferences ->
            preferences.remove(DISPLAY_TEXT_KEY)
            preferences.remove(DIE_ONE_KEY)
            preferences.remove(DIE_TWO_KEY)
        }
    }
}
