package com.example.to_do_project
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.ui.HomeViewModel
import com.example.to_do_project.ui.theme.ToDoProjectTheme

class achievemntactivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoProjectTheme {
                achievementscreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun achievementscreen() {

    Scaffold {


            Box(
                modifier = Modifier
                    .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
            ) {
                TextField(
                    value = "",
                    onValueChange ={},
                    modifier = Modifier
                        .padding(end = 50.dp, top = 10.dp)
                        .size(90.dp)
                        .border(1.dp, Color.Black)
                        .align(Alignment.CenterEnd),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedLabelColor = Color.Black,
                        containerColor = Color.Blue.copy(alpha = 0.8f),

                )
                )
                TextField(
                    value = "",
                    onValueChange ={},
                    modifier = Modifier
                        .padding(end = 250.dp, top = 10.dp)
                        .size(90.dp)
                        .border(1.dp, Color.Black)
                        .align(Alignment.CenterEnd),
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedLabelColor = Color.Black,
                        containerColor = Color.Blue.copy(alpha = 0.8f),

                        )
                )
                Text(
                    text = "Achievements",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.TopCenter)
                )
                Text(
                    text = "Unchecked To-Do",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(end = 250.dp, bottom = 150.dp, start = 70.dp, top = 10.dp)
                        .align(Alignment.CenterEnd)
                )
                Text(
                    text = "Checked To-Do",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .padding(end = 40.dp, bottom = 150.dp, start = 70.dp, top = 20.dp)
                        .align(Alignment.CenterEnd)
                )
                Image(
                    painter = painterResource(id = R.drawable.achi),
                    contentDescription = "",
                    modifier = Modifier
                        .size(400.dp)
                        .padding(top = 40.dp)
                )
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    ToDoProjectTheme {
        achievementscreen()
    }
}