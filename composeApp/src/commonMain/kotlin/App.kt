import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.seiko.imageloader.LocalImageLoader
import imageLoader.generateImageLoader
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import planets.di.planetsModule
import theme.PlanetsKMPTheme

@Composable
@Preview
fun App() = PlanetsKMPTheme {

    val navController = rememberNavController()

    KoinApplication(
        application = {
            modules(
                kstoreModule(),
                planetsModule
            )
        }
    ){
        CompositionLocalProvider(
            LocalImageLoader provides remember { generateImageLoader() },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Navigation(
                    navController = navController
                )
            }
        }

    }

}