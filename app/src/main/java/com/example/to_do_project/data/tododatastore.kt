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
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "textfield")
data class tododatastore(var context:Context)
{
    private val dataStore=context.dataStore
    companion object{
        val TEXT_FIELDS_KEY= stringSetPreferencesKey("text_fields_key")
        val CHECKBOX_KEY= booleanPreferencesKey("checkbox_key")
    }
    val checkboxState:kotlinx.coroutines.flow.Flow<Boolean> =dataStore.data
        .map{ preferences ->
            preferences[CHECKBOX_KEY] ?:false
        }
    val textFieldsFlow: kotlinx.coroutines.flow.Flow<List<String>> = dataStore.data
        .map{ preferences ->
            // `Set<String>`'i `List<String>`'e dönüştür
            preferences[TEXT_FIELDS_KEY]?.toList() ?: emptyList()
        }
    suspend fun savetextfields(textFields:List<String>){
        dataStore.edit { preferences ->
            preferences[TEXT_FIELDS_KEY]=textFields.toSet()
        }

    }
    suspend fun saveCheckboxState(isChecked:Boolean){
        dataStore.edit { preferences ->
            preferences[CHECKBOX_KEY]=isChecked
        }
    }

}
