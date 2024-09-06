package com.example.to_do_project.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.ui.theme.ToDoProjectTheme
import com.example.to_do_project.R
import com.example.to_do_project.achievementscreen


@Composable
fun navigationbar(navController: NavHostController = rememberNavController()) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val currentColor = remember { mutableStateOf(Color.Blue) }


    val colors = listOf(Color.Blue, Color.Red, Color.Black, Color.Magenta, Color.Cyan)
    fun changeColor() {
        val currentIndex = colors.indexOf(currentColor.value)
        val nextIndex = (currentIndex + 1) % colors.size
        currentColor.value = colors[nextIndex]
    }

    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            // Home composable'ınızı buraya ekleyin
        }
        composable("achievement") {
            achievementscreen(viewModel = homeViewModel)
        }
    }


    BottomAppBar( modifier = Modifier.height(60.dp),
        containerColor = currentColor.value,
        content = {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                IconButton(onClick = { navController.navigate("Home")  }) {
                    Icon(painter = painterResource(id = R.drawable.homeicon), contentDescription = "", tint = Color.Unspecified, modifier = Modifier.size(40.dp))

                }
            }

            Box(modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)){
                IconButton(onClick = { navController.navigate("achievement") }) {
                    Image(painter = painterResource(id = R.drawable.achilogo), contentDescription = "")


                }
            }
            Box(modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)){
                IconButton(onClick = {
                    changeColor()

                }) {
                    Image(painter = painterResource(id = R.drawable.theme), contentDescription = "")
                }
            }

        }

    )



}


