package com.twmaze.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("movies_datastore")

class DataStoreManager(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val SAVED_SHOW_IDS = stringSetPreferencesKey("saved_show_ids")
    }

    suspend fun saveShowId(showId: String) {
        dataStore.edit {
            val savedIdsSet = it[SAVED_SHOW_IDS]?.toMutableSet() ?: mutableSetOf()
            savedIdsSet.add(showId)
            it[SAVED_SHOW_IDS] = savedIdsSet
        }
    }

    suspend fun deleteShowId(showId: String) {
        dataStore.edit {
            val savedIdsSet = it[SAVED_SHOW_IDS]?.toMutableSet() ?: return@edit
            savedIdsSet.remove(showId)
            it[SAVED_SHOW_IDS] = savedIdsSet
        }
    }

    val savedShowIds: Flow<Set<String>> = dataStore.data.map {
        it[SAVED_SHOW_IDS] ?: emptySet()
    }

}