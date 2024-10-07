package com.example.to_do_project

import android.annotation.SuppressLint
import android.health.connect.datatypes.units.Percentage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.data.tododata
import com.example.to_do_project.ui.Home
import com.example.to_do_project.ui.HomeViewModel
import com.example.to_do_project.ui.theme.ToDoProjectTheme
import kotlinx.coroutines.delay


class achievemntactivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoProjectTheme {
                val viewModel: HomeViewModel by viewModels()
                achievementscreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun achievementscreen(viewModel: HomeViewModel = hiltViewModel()) {
    val checkedcount by viewModel.checkedCheckboxCount.collectAsState()
    val uncheckedCount = viewModel.textFields.collectAsState().value.size - checkedcount
    val totalCheckboxCount = viewModel.textFields.collectAsState().value.size
    var showPopup by remember { mutableStateOf(false) }

    val checkedPercentage = if (totalCheckboxCount > 0) {
        (checkedcount.toFloat() / totalCheckboxCount.toFloat()) * 100
    } else {
        0f
    }
    Scaffold {
        Box(
            modifier = Modifier
                .border(11.dp, color = Color.Blue)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 79.dp, start = 30.dp)
                    .size(70.dp)
                    .align(
                        Alignment.TopStart
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.capture),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 69.dp, start = 20.dp)
                    .size(90.dp)
                    .align(
                        Alignment.TopStart
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.planning),
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 450.dp)
                    .size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.failure),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 250.dp)
                    .size(100.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.excellence),
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 150.dp, start = 30.dp)
                    .size(120.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.kupa),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 550.dp)
                    .size(120.dp)
            )
            Image(colorFilter = ColorFilter.tint(Color.Cyan),
                painter = painterResource(id = R.drawable.capture),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 16.dp, bottom= 1.dp)
                    .height(41.dp,)
                    .padding(start = 280.dp)
                    .size(90.dp)
                    .align(
                        Alignment.Center
                    )
            )
            Text(
                text = "Checked To-Do Count: $checkedcount",
                fontSize = 24.sp,
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Thin,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 1.dp)
                    .align(Alignment.Center)
            )
            Image(colorFilter = ColorFilter.tint(Color.Blue),
                painter = painterResource(id = R.drawable.capture),
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom= 350.dp, end = 17.dp)
                    .height(41.dp,)
                    .padding(start = 280.dp)
                    .size(90.dp)
            )
            Text(
                text = "Unchecked To-Do Count: $uncheckedCount",
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Thin,
                modifier = Modifier.padding(8.dp, top = 400.dp)
            )
            Image(colorFilter = ColorFilter.tint(Color.Red),
                painter = painterResource(id = R.drawable.capture),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 400.dp, start= 38.dp)
                    .height(41.dp,)
                    .padding(start = 280.dp)
                    .size(90.dp)
                    .align(
                        Alignment.Center
                    )

            )

            Text(
                text = "Total To-Do Count: $totalCheckboxCount",
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Thin,
                modifier = Modifier.padding(9.dp, bottom = 350.dp)
            )

            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.04f)
            ) {
                Text(
                    text = "%%%.2f  Success".format(checkedPercentage).replace(".", ","),
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
        LaunchedEffect(checkedPercentage) {
            if (checkedPercentage.toInt() == 100 && !showPopup) {
                showPopup = true
                delay(3000)
                showPopup = false
            }
        }
        if (showPopup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f)),
                contentAlignment = Alignment.Center
            ) {
                var colorIndex by remember { mutableStateOf(0) }



                val transition = updateTransition(targetState = colorIndex, label = "Color Transition")


                val colors = listOf(Color.Red, Color.Green, Color.Blue)
                val backgroundColor by transition.animateColor(
                    label = "Background Color"
                ) { state ->
                    colors[state % colors.size]

                }

                LaunchedEffect(Unit) {
                    while (true) {
                        delay(280)
                        colorIndex = (colorIndex + 1) % colors.size
                    }
                }

                Column {


                    Text(
                        text = "%100 Success",
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 32.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .background(backgroundColor)
                            .padding(16.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.kupa),
                        contentDescription = "",
                        Modifier
                            .size(180.dp)
                            .padding(start = 65.dp, top = 50.dp)
                    )
                }
            }
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