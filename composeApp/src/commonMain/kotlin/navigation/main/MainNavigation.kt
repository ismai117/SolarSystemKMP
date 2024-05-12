package navigation.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import main.planets.presentation.PlanetDetailScreen
import main.planets.presentation.PlanetsListScreen
import main.planets.presentation.PlanetsViewModel


@Composable
fun MainNavigation(
    navController: NavController
){

    NavHost(
        navController  = navController as NavHostController,
        startDestination = PLANETS_LIST_SCREEN_ROUTE
    ){
        composable(
            route = PLANETS_LIST_SCREEN_ROUTE
        ){
            PlanetsListScreen(
                navigateToPlanetDetailScreen = { planetId ->
                    navController.navigate(PLANET_DETAIL_SCREEN_ROUTE + "/${planetId}")
                }
            )
        }
        composable(
            route = "$PLANET_DETAIL_SCREEN_ROUTE/{planetId}",
            arguments = listOf(navArgument("planetId") { type = NavType.IntType })
        ){backStackEntry ->
            backStackEntry.arguments?.getInt("planetId")?.let { planetId ->
                PlanetDetailScreen(
                    planetId = planetId,
                    navigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}