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
import com.example.to_do_project.data.tododatastore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext context:Context,private val preferenceManager: tododatastore):ViewModel(){
    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        private val TEXT_FIELDS_KEY = stringSetPreferencesKey("text_fields_key")
    }

    private val _textFields = MutableStateFlow<List<String>>(emptyList())
    val textFields: StateFlow<List<String>> = _textFields

    private val _checkboxStates = MutableStateFlow<List<Boolean>>(emptyList())
    val checkboxStates: StateFlow<List<Boolean>> = _checkboxStates

    init {
        viewModelScope.launch {
            dataStore.data
                .map { preferences ->
                    preferences[TEXT_FIELDS_KEY]?.toList() ?: emptyList()
                }
                .collect { savedTextFields ->
                    _textFields.value = savedTextFields
                    updateCheckboxStates(savedTextFields.size)
                }
        }
    }

    private fun updateCheckboxStates(size: Int) {
        viewModelScope.launch {
            preferenceManager.checkboxStatesFlow(size)
                .collect { states ->
                    _checkboxStates.value = states
                }
        }
    }

    fun addTextField(text: String) {
        val updatedList = _textFields.value + text
        _textFields.value = updatedList
        saveTextFields(updatedList)
        // Add initial checkbox state
        addInitialCheckboxStates(updatedList.size)
    }

    fun removeTextField(index: Int) {
        val updatedList = _textFields.value.toMutableList().apply { removeAt(index) }
        _textFields.value = updatedList
        saveTextFields(updatedList)
        removeCheckboxState(index)
    }

    private fun addInitialCheckboxStates(size: Int) {
        viewModelScope.launch {
            val existingStates = _checkboxStates.value
            val newStates = List(size) { index -> existingStates.getOrElse(index) { false } }
            _checkboxStates.value = newStates
            for (i in newStates.indices) {
                preferenceManager.saveCheckboxState(i, newStates[i])
            }
        }
    }

    private fun removeCheckboxState(index: Int) {
        viewModelScope.launch {
            _checkboxStates.value = _checkboxStates.value.toMutableList().apply { removeAt(index) }
            preferenceManager.saveCheckboxState(index, false) // Reset state or remove key
        }
    }

    fun updateTodo(index: Int, newText: String) {
        val updatedList = _textFields.value.toMutableList().apply { set(index, newText) }
        _textFields.value = updatedList
        saveTextFields(updatedList)
    }

    fun onCheckboxClicked(index: Int, isChecked: Boolean) {
        viewModelScope.launch {
            preferenceManager.saveCheckboxState(index, isChecked)
            _checkboxStates.value = _checkboxStates.value.toMutableList().apply { set(index, isChecked) }
        }
    }

    private fun saveTextFields(textFields: List<String>) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[TEXT_FIELDS_KEY] = textFields.toSet()
            }
        }
    }

}