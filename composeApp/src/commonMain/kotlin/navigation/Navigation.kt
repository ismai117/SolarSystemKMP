package navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import planets.PlanetDetailScreen
import planets.PlanetsScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(
    navController: NavController
){
    SharedTransitionLayout {
        NavHost(
            navController = navController as NavHostController,
            startDestination = PLANETS_SCREEN_ROUTE
        ) {
            composable(
                route = PLANETS_SCREEN_ROUTE
            ) {
                PlanetsScreen(
                    animatedVisibilityScope = this,
                    navigateToPlanetDetailScreen = { planetId ->
                        navController.navigate(PLANET_DETAIL_SCREEN_ROUTE + "/${planetId}")
                    }
                )
            }
            composable(
                route = "$PLANET_DETAIL_SCREEN_ROUTE/{planetId}",
                arguments = listOf(navArgument("planetId") { type = NavType.IntType })
            ) { backStackEntry ->
                backStackEntry.arguments?.getInt("planetId")?.let { planetId ->
                    PlanetDetailScreen(
                        animatedVisibilityScope = this,
                        planetId = planetId,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
