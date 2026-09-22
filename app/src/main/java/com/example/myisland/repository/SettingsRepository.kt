package com.example.myisland.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "island_settings")

@Singleton
class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        val KEY_WIDTH = intPreferencesKey("island_width")
        val KEY_HEIGHT = intPreferencesKey("island_height")
        val KEY_Y_OFFSET = intPreferencesKey("island_y_offset")
        val KEY_OPACITY = floatPreferencesKey("island_opacity")
        val KEY_MASTER_POWER = booleanPreferencesKey("master_power")
    }

    val widthFlow: Flow<Int> = dataStore.data.map { prefs -> prefs[KEY_WIDTH] ?: 120 }
    val heightFlow: Flow<Int> = dataStore.data.map { prefs -> prefs[KEY_HEIGHT] ?: 36 }
    val yOffsetFlow: Flow<Int> = dataStore.data.map { prefs -> prefs[KEY_Y_OFFSET] ?: 24 }
    val opacityFlow: Flow<Float> = dataStore.data.map { prefs -> prefs[KEY_OPACITY] ?: 1.0f }
    val masterPowerFlow: Flow<Boolean> = dataStore.data.map { prefs -> prefs[KEY_MASTER_POWER] ?: true }

    suspend fun setWidth(width: Int) {
        dataStore.edit { it[KEY_WIDTH] = width }
    }
    
    suspend fun setHeight(height: Int) {
        dataStore.edit { it[KEY_HEIGHT] = height }
    }
    
    suspend fun setYOffset(yOffset: Int) {
        dataStore.edit { it[KEY_Y_OFFSET] = yOffset }
    }
    
    suspend fun setMasterPower(isEnabled: Boolean) {
        dataStore.edit { it[KEY_MASTER_POWER] = isEnabled }
    }
}
