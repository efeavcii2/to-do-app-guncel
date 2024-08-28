package com.example.to_do_project.ui

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topappbar(navController: NavHostController = rememberNavController()) {

    val context = LocalContext.current.applicationContext
    androidx.compose.material3.TopAppBar(title = {
        Text(
            text = "To - Do",
            fontFamily = FontFamily.Monospace,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            color = Color.White,

            )

    },
        navigationIcon = {
            IconButton(onClick = { Toast.makeText(context, "To-Do", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    painter = painterResource(id = R.drawable.logooo),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(100.dp)
                )


            }

        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,


            ), actions = {

        }
    )


}

