package com.example.to_do_project.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.ui.theme.ToDoProjectTheme

@Composable
fun navigationbar(navController: NavHostController = rememberNavController()) {
    val navController= rememberNavController()


    NavHost(navController = navController, startDestination ="Home" , modifier = Modifier.padding()) {
        composable("Home"){
            LaunchedEffect(Unit) {


            }
        }
        composable("datescreen"){
            datescreen()
        }
    }

    BottomAppBar( modifier = Modifier.height(60.dp),
        containerColor = Color.Blue,
        content = {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                IconButton(onClick = { navController.navigate("Home")  }) {
                    Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.White)

                }
            }
            /*
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ){
                IconButton(onClick = { navController.navigate("datescreen") }) {

                    Icon(Icons.Filled.DateRange, contentDescription = "Date", tint = Color.White)

                }
            }

             */
            Box(modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "", tint = Color.White)


                }
            }

        }

    )



}


