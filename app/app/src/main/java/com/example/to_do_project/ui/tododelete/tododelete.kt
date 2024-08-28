package com.example.to_do_project.ui.tododelete

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.R
import com.example.to_do_project.ui.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun delete(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {

    val textFields by viewModel.textFields.collectAsState(initial = emptyList())

    textFields.forEachIndexed { index, text ->

        IconButton(onClick = { viewModel.removeTextField(index) }) { // To-Do silme işlemi
            Image(
                painter = painterResource(id = R.drawable.deleteimg),
                contentDescription = ""
            )

        }


    }


}


