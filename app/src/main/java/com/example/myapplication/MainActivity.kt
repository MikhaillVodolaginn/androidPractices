package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.navigation.BottomNavigationBar
import com.example.myapplication.presentation.navigation.NavigationGraph
import com.example.myapplication.presentation.theme.MyApplicationTheme
import com.example.myapplication.presentation.viewModel.CompanyAppViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    AppNavigation(navController)
}

@Composable
fun AppNavigation(navigationController: NavHostController) {
    val viewModel = koinViewModel<CompanyAppViewModel>()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navigationController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavigationGraph(
                navController = navigationController,
                viewModel
            )
        }
    }
}
