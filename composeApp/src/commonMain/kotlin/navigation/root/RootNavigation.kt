package navigation.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import main.MainScreen

@Composable
fun RootNavigation(
    navController: NavController
){
    NavHost(
        navController  = navController as NavHostController,
        startDestination = MAIN_SCREEN_ROUTE
    ){
        composable(
            route = MAIN_SCREEN_ROUTE
        ){
            MainScreen()
        }
    }
}