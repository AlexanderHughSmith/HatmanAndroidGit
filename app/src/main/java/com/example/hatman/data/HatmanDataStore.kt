package com.example.hatman.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HatmanDataStore(private val context: Context) {

    companion object{
        private val Context.dataStore by preferencesDataStore(name = "HatmanDataStore")
        val DISPLAY_TEXT_KEY = stringPreferencesKey("display_text")
        val DIE_ONE = stringPreferencesKey("die_one")
        val DIE_TWO = stringPreferencesKey("die_two")
        val DYNAMIC_COLORS = stringPreferencesKey("dynamic_colors")
        val DARK_THEME = stringPreferencesKey("dark_theme")
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

    val getDieOne: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DIE_ONE] ?: "1"
        }

    suspend fun saveDieOne(dieOne: Int) {
        context.dataStore.edit { preferences ->
            preferences[DIE_ONE] = dieOne.toString()
        }
    }

    val getDieTwo: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DIE_TWO] ?: "2"
        }

    suspend fun saveDieTwo(dieTwo: Int) {
        context.dataStore.edit { preferences ->
            preferences[DIE_TWO] = dieTwo.toString()
        }
    }

    val getDarkTheme: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_THEME] ?: ""
        }

    suspend fun saveDarkTheme(darkTheme: Boolean?) {
        if(darkTheme != null){
            context.dataStore.edit { preferences ->
                preferences[DARK_THEME] = darkTheme.toString()
            }
        }
        else{
            context.dataStore.edit { preferences ->
                preferences[DARK_THEME] = ""
            }
        }
    }

    val getDynamicColors: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[DYNAMIC_COLORS] ?: ""
        }

    suspend fun saveDynamicColors(dynamicColors: Boolean?) {
        if(dynamicColors != null){
            context.dataStore.edit { preferences ->
                preferences[DYNAMIC_COLORS] = dynamicColors.toString()
            }
        }
        else{
            context.dataStore.edit { preferences ->
                preferences[DYNAMIC_COLORS] = ""
            }
        }
    }

    suspend fun resetDataStore() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
