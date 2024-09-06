package com.example.to_do_project.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_do_project.R
import com.example.to_do_project.ui.theme.ToDoProjectTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.achievementscreen
import com.example.to_do_project.ui.todocreate.create
import com.example.to_do_project.ui.todocreate.create

import com.example.to_do_project.ui.tododelete.delete
import com.example.to_do_project.ui.todouptade.uptade

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoProjectTheme {
                val viewModel: HomeViewModel by viewModels()
                Home(viewModel, navController = rememberNavController())

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {}
                    composable("achievement") { }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(viewModel: HomeViewModel = hiltViewModel(), navController: NavHostController) {
    //val textFields by remember { mutableStateOf(viewModel.todoList) }
    val textFields by viewModel.textFields.collectAsState(initial = emptyList())


    Scaffold(
        topBar = {
            topappbar()

        },
        content = {

            uptade()
            delete()
        },
        floatingActionButton = {
            create()
            },
        bottomBar = {
            navigationbar()
        },



    )



}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoProjectTheme {
        // Home()

    }
}