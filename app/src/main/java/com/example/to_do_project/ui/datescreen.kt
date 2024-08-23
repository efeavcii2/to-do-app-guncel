package com.example.to_do_project.ui

import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun datescreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val selectedDate = remember {
        mutableStateOf("")
    }

    fun showdate() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = android.app.DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                selectedDate.value = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            }, year, month, day
        )
        datePickerDialog.show()

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(modifier = Modifier.align(Alignment.BottomEnd), onClick = {
            coroutineScope.launch {
                showdate()

            }
        }) {
            Text(text = "Tarih Seç")
        }

        // Seçilen tarihi göster
        if (selectedDate.value.isNotEmpty()) {
            Text(
                text = "Seçilen Tarih: ${selectedDate.value}",
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }

    }


}