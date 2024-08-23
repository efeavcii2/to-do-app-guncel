package com.example.to_do_project.ui

import android.content.Context
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_project.data.dataStore
import com.example.to_do_project.data.tododata
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext context:Context):ViewModel(){
    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        private val TEXT_FIELDS_KEY = stringSetPreferencesKey("text_fields_key")
    }

    private val _textFields = MutableStateFlow<List<String>>(emptyList())
    val textFields: Flow<List<String>> = _textFields

    init {
        viewModelScope.launch {
            dataStore.data
                .map { preferences ->
                    preferences[TEXT_FIELDS_KEY]?.toList() ?: emptyList()
                }
                .collect { savedTextFields ->
                    _textFields.value = savedTextFields
                }
        }
    }

    fun addTextField(text: String) {
        //val currentList=_textFields.value
        //if(currentList.size<7) {
            val updatedList = _textFields.value + text
            _textFields.value = updatedList
            saveTextFields(updatedList)
        //}
    }

    fun removeTextField(index: Int) {
        val updatedList = _textFields.value.toMutableList().apply { removeAt(index) }
        _textFields.value = updatedList
        saveTextFields(updatedList)
    }

    private fun saveTextFields(textFields: List<String>) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[TEXT_FIELDS_KEY] = textFields.toSet()
            }
        }
    }
    fun updateTodo(index: Int, newText: String) {
        val updatedList = _textFields.value.toMutableList().apply { set(index, newText) }
        _textFields.value = updatedList
        saveTextFields(updatedList)

    }

}