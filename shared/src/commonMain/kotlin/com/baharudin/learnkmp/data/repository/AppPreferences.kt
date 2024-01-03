package com.baharudin.learnkmp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

data class AppPreferences(
    val token : String? = null,
)

class AppPreferencesRepository(
    private val dataStore : DataStore<Preferences>
) : KoinComponent {
    private val logger = Logger.withTag("UserPreferencesManager")
    private object PreferencesKey {
        val USER_TOKEN = stringPreferencesKey("USER_TOKEN")
    }

    suspend fun clear() {
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun fetchInitialPreferences() =
        mapAppPreferences(dataStore.data.first().toPreferences())

    val userPreferencesFlow : Flow<AppPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                logger.d { "Error reading preference : $exception" }
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapAppPreferences(preferences)
        }

    suspend fun setUserToken(userToken : String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.USER_TOKEN] = userToken
        }
    }
    private fun mapAppPreferences(preferences: Preferences) : AppPreferences {
        val userToken = preferences[PreferencesKey.USER_TOKEN] ?: ""
        Logger.d { "lastScreen : $userToken" }
        return AppPreferences(userToken)
    }
}
