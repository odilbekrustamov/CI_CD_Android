package uz.example.cicd

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.example.cicd.presentation.screen.home.HomeScreen
import uz.example.cicd.presentation.screen.task.TaskScreen
import uz.example.cicd.ui.theme.CICDAppTheme
import android.os.Bundle

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CICDAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            onTaskClick = {
                                navController.navigate("task")
                            },
                            onAddClick = {
                                navController.navigate("task")
                            },
                        )
                    }
                    composable("task") {
                        TaskScreen(
                            onBack = {
                                navController.popBackStack()
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CICDAppTheme {
        LoginScreen(
            onLoginClicked = { email, password ->
            },
        )
    }
}
