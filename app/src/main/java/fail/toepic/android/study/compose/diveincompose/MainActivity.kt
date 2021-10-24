package fail.toepic.android.study.compose.diveincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fail.toepic.android.study.compose.diveincompose.ui.mainlist.MainListScreen
import fail.toepic.android.study.compose.diveincompose.ui.nav.Screen
import fail.toepic.android.study.compose.diveincompose.ui.navigation.NavigationMainScreen
import fail.toepic.android.study.compose.diveincompose.ui.theme.DiveInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveInComposeTheme {
                // A surface container using the 'background' color from the theme

                val navController = rememberNavController()

                Scaffold {
                    NavHost(navController = navController, startDestination = "MainList"){
                        composable(route = Screen.MainList.route) {
                            MainListScreen{ src ->
                                navController.navigate(src.route)
                            }
                        }
                        composable(route = Screen.NavigationMain.route){
                            NavigationMainScreen{ src ->
                                navController.navigate(src.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DiveInComposeTheme {
        Greeting("Android")
    }
}