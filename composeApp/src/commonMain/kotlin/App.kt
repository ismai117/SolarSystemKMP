import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import di.dataModule
import main.planets.di.planetsModule
import navigation.root.RootNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import theme.PlanetsKMPTheme


@Composable
@Preview
fun App() = PlanetsKMPTheme {

    val navController = rememberNavController()

    KoinApplication(
        application = {
            modules(
                dataModule(),
                planetsModule
            )
        }
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            RootNavigation(
                navController = navController
            )
        }
    }

}