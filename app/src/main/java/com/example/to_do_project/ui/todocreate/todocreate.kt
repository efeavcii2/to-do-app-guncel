package com.example.to_do_project.ui.todocreate

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.R
import com.example.to_do_project.ui.HomeViewModel
import com.example.to_do_project.ui.datescreen

@Composable
fun create(viewModel: HomeViewModel = hiltViewModel(),navController: NavHostController = rememberNavController()){
    val textFields by viewModel.textFields.collectAsState(initial = emptyList())
    FloatingActionButton(modifier = Modifier.size(50.dp),

        onClick = {
            viewModel.addTextField("") // Yeni boş bir To-Do ekle
        },
        containerColor = Color.Blue,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ekleimg),
                contentDescription = "Add To-Do",
                tint = Color.White,
                modifier = Modifier.size(30.dp)



                )

        }
    )



}


@Preview(showBackground = true)
@Composable
fun PreviewTopAppBar() {
    create(navController = rememberNavController())
}

