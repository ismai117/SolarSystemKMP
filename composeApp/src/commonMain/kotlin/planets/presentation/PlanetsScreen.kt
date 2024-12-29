package planets.presentation

import Type
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import coil3.compose.AsyncImage
import getPlatform
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import planets.domain.domain.Planet

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PlanetsScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navigateToPlanetDetailScreen: (Int) -> Unit,
) {

    val planetsViewModel = koinInject<PlanetsViewModel>()
    val state by planetsViewModel.state.collectAsState()

    val pagerState = rememberPagerState { state.planets.size }
    val scope = rememberCoroutineScope()

    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val isCompacted = windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

    Scaffold { paddingValues ->

        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {

            Column(
                modifier = modifier.fillMaxSize()
            ) {

                HorizontalPager(
                    state = pagerState,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    userScrollEnabled = when {
                        getPlatform().type == Type.MOBILE -> true
                        getPlatform().type == Type.NON_MOBILE && isCompacted -> true
                        else -> false
                    }
                ) { page ->

                    PageItem(
                        animatedVisibilityScope = animatedVisibilityScope,
                        planet = state.planets.sortedBy { it.planetOrder }[page],
                        navigateToPlanetDetailScreen = navigateToPlanetDetailScreen
                    )

                }

                PageNavigator(
                    currentPage = pagerState.currentPage,
                    lastIndex = state.planets.lastIndex,
                    animateScrollToPage = { page ->
                        scope.launch { pagerState.animateScrollToPage(page) }
                    }
                )

            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = modifier.align(Alignment.Center)
                )
            }

        }

    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PageItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    navigateToPlanetDetailScreen: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp)
        ) {
            AsyncImage(
                model = planet.img,
                contentDescription = planet.imgDescription,
                modifier = modifier
                    .fillMaxSize()
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${planet.planetId}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)
                        }
                    )
            )
        }

        Spacer(modifier = modifier.padding(12.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = planet.name,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = modifier.padding(12.dp))

            OutlinedButton(
                onClick = {
                    navigateToPlanetDetailScreen(planet.planetId)
                }
            ) {
                Text(
                    text = "View",
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }

}

@Composable
fun PageNavigator(
    modifier: Modifier = Modifier,
    currentPage: Int,
    lastIndex: Int,
    animateScrollToPage: (Int) -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = { animateScrollToPage(0) }
        ) {
            Icon(
                imageVector = Icons.Default.SkipPrevious,
                contentDescription = null
            )
        }
        Spacer(modifier = modifier.padding(8.dp))
        IconButton(
            onClick = { animateScrollToPage(currentPage - 1) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = null
            )
        }
        Spacer(modifier = modifier.padding(8.dp))
        IconButton(
            onClick = { animateScrollToPage(currentPage + 1) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
        Spacer(modifier = modifier.padding(8.dp))
        IconButton(
            onClick = { animateScrollToPage(lastIndex) }
        ) {
            Icon(
                imageVector = Icons.Default.SkipNext,
                contentDescription = null
            )
        }
    }
}