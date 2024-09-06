package com.example.to_do_project.ui.todouptade

import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.R
import com.example.to_do_project.data.tododatastore
import com.example.to_do_project.ui.HomeViewModel
import com.example.to_do_project.ui.theme.ToDoProjectTheme
import com.example.to_do_project.ui.tododelete.delete

//import com.example.to_do_project.ui.tododelete.delete

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun uptade(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val checkboxStates by viewModel.checkboxStates.collectAsState()
    val textFields by viewModel.textFields.collectAsState(initial = emptyList())
    val context = LocalContext.current
    fun openDatePicker(index: Int) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = android.app.DatePickerDialog(
            context,
            { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                viewModel.updateTodo(index, selectedDate)
            }, year, month, day
        )
        datePickerDialog.show()
    }

    LazyColumn(modifier = Modifier.padding(50.dp)) {
        items(textFields.size) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .padding(top = 10.8.dp)
            ) {
                OutlinedTextField(
                    value = textFields[index],
                    onValueChange = { newText ->
                        val prefix = textFields[index].substringBefore(": ") + ": "
                        if (newText.startsWith(prefix)) {
                            viewModel.updateTodo(index, newText)
                        } else if (newText.length >= prefix.length) {
                            val suffix = newText.substring(prefix.length)
                            viewModel.updateTodo(index, prefix + suffix)
                        } else {
                            viewModel.updateTodo(index, prefix)
                        }
                    },
                    leadingIcon = {
                        IconButton(onClick = { openDatePicker(index) }) {
                            Image(painter = painterResource(id = R.drawable.dateicon), contentDescription ="" )
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = { viewModel.removeTextField(index) }) {
                            Image(
                                painter = painterResource(id = R.drawable.deleteicon),
                                contentDescription = "Delete To-Do",

                            )
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedLabelColor = Color.Black,
                        containerColor = Color.Blue.copy(alpha = 0.8f),
                    ),
                    shape = CutCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    label = { Text(text = "To-Do ${index + 1}") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                // Checkbox

                Checkbox(
                    checked = checkboxStates.getOrElse(index) { false },
                    onCheckedChange = { isChecked ->
                        viewModel.onCheckboxClicked(index, isChecked)
                    },
                    modifier = Modifier.padding(top = 15.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Blue,
                        uncheckedColor = Color.Blue,
                        checkmarkColor = Color.White

                    )
                )
            }
        }
    }
}