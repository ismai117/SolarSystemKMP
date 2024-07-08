import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil3.annotation.ExperimentalCoilApi
import coil3.asyncImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.enableDiskCache
import navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import planets.di.planetsModule
import theme.PlanetsKMPTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App(disableDiskCache: Boolean = false) = PlanetsKMPTheme {

    val navController = rememberNavController()

    KoinApplication(
        application = {
            modules(
                kstoreModule(),
                planetsModule
            )
        }
    ){

        setSingletonImageLoaderFactory { context ->
            if (disableDiskCache) context.asyncImageLoader() else
                context.asyncImageLoader().enableDiskCache()
        }

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