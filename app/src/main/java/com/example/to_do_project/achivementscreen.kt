package com.example.to_do_project
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_project.data.tododata
import com.example.to_do_project.ui.Home
import com.example.to_do_project.ui.HomeViewModel
import com.example.to_do_project.ui.theme.ToDoProjectTheme

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
    val checkedPercentage = if (totalCheckboxCount > 0) {
        (checkedcount.toFloat() / totalCheckboxCount.toFloat()) * 100
    } else {
        0f
    }
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Checked To-Do Count: $checkedcount",
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 250.dp)
                    .align(Alignment.Center)
            )
            Text(
                text = "Unchecked To-Do Count: $uncheckedCount",
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(8.dp, bottom = 150.dp, top = 350.dp)
            )
            Text(
                text = "Total To-Do Count: $totalCheckboxCount",
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(8.dp, bottom = 50.dp, top = 650.dp)
            )
            Text(
                text = "%%%.2f".format(checkedPercentage).replace(".", ","),
                fontSize = 24.sp,
                fontFamily = FontFamily.Monospace,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(8.dp)
            )
            Image(painter = painterResource(id = R.drawable.achilogo), contentDescription = "", modifier = Modifier.padding(bottom = 450.dp))
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