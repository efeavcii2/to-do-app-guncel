package com.example.to_do_project.data

import android.content.Context
import android.preference.PreferenceDataStore
import android.widget.CheckBox
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.mutablePreferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.to_do_project.ui.HomeViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "textfield")
data class tododatastore @Inject constructor(@ApplicationContext var context:Context)
{
    private val dataStore = context.dataStore

    companion object {
        fun checkboxKey(index: Int) = booleanPreferencesKey("checkbox_key_$index")
        private val TOP_APP_BAR_COLOR_KEY = stringPreferencesKey("top_app_bar_color")


    }
    suspend fun saveTopAppBarColor(colorHex: String) {
        dataStore.edit { preferences ->
            preferences[TOP_APP_BAR_COLOR_KEY] = colorHex
        }
    }

    fun getTopAppBarColorFlow(): kotlinx.coroutines.flow.Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOP_APP_BAR_COLOR_KEY]
        }
    }

    fun checkboxStatesFlow(textFieldsSize: Int): kotlinx.coroutines.flow.Flow<List<Boolean>> {
        return dataStore.data.map { preferences ->
            List(textFieldsSize) { index ->
                preferences[checkboxKey(index)] ?: false
            }
        }
    }

    suspend fun saveTextFields(textFields: List<String>) {
        dataStore.edit { preferences ->
            preferences[stringSetPreferencesKey("text_fields_key")] = textFields.toSet()
        }
    }

    suspend fun saveCheckboxState(index: Int, isChecked: Boolean) {
        dataStore.edit { preferences ->
            preferences[checkboxKey(index)] = isChecked
        }
    }
}