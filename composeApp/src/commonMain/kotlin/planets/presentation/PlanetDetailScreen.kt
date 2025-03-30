package planets.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import coil3.compose.AsyncImage
import getPlatform
import org.koin.compose.koinInject
import planets.domain.domain.Planet


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PlanetDetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    planetId: Int,
    navigateBack: () -> Unit
) {
    val planetsViewModel = koinInject<PlanetsViewModel>()
    val state by planetsViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        planetsViewModel.getPlanetById(planetId)
    }

    state.planet?.let { planet ->
        PlanetDetailScreenContent(
            animatedVisibilityScope = animatedVisibilityScope,
            planet = planet,
            navigateBack = navigateBack
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PlanetDetailScreenContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    navigateBack: () -> Unit
) {
    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val isCompacted = windowAdaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

    Scaffold { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            if (getPlatform().type == Type.MOBILE || isCompacted) {
                PlanetDetailMobileContent(
                    animatedVisibilityScope = animatedVisibilityScope,
                    planet = planet,
                    navigateBack = navigateBack
                )
            } else {
                PlanetDetailNonMobileContent(
                    animatedVisibilityScope = animatedVisibilityScope,
                    planet = planet,
                    navigateBack = navigateBack
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.PlanetDetailMobileContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    navigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(name = planet.name, navigateBack = navigateBack)
        PlanetImage(
            modifier = modifier,
            animatedVisibilityScope = animatedVisibilityScope,
            planet = planet,
            scrollState = scrollState
        )
        PlanetDetailItems(planet = planet)
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.PlanetDetailNonMobileContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    navigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxSize()) {
        TopBar(name = planet.name, navigateBack = navigateBack)
        Row(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                PlanetImage(
                    modifier = modifier,
                    animatedVisibilityScope = animatedVisibilityScope,
                    planet = planet,
                    scrollState = scrollState
                )
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    modifier = modifier
                        .padding(top = 80.dp, start = 24.dp, end = 24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PlanetDetailItems(planet = planet)
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.PlanetImage(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    scrollState: ScrollState
) {
    Box(
        modifier = modifier
            .padding(top = 40.dp)
            .size(300.dp)
            .clip(CircleShape)
            .parallaxLayoutModifier(scrollState, 2)
    ) {
        AsyncImage(
            model = planet.img,
            contentDescription = planet.imgDescription,
            modifier = modifier
                .fillMaxSize()
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "image/${planet.planetId}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 1000)
                    }
                )
        )
    }
}

@Composable
private fun PlanetDetailItems(
    modifier: Modifier = Modifier,
    planet: Planet
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Description",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = planet.description,
            fontSize = 16.sp
        )
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DetailCard(label = "Order", value = planet.planetOrder.toString())
        DetailCard(label = "Mass", value = planet.mass)
        DetailCard(label = "Volume", value = planet.volume)
        DetailCardWithIcon(
            label = planet.source,
            icon = Icons.Default.Link,
            onClick = { getPlatform().openUrl(planet.wikiLink) }
        )
    }
}

@Composable
private fun DetailCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart)
            )
            Text(
                text = value,
                fontSize = 16.sp,
                modifier = modifier
                    .padding(end = 16.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Composable
private fun DetailCardWithIcon(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart)
            )
            IconButton(
                onClick = onClick,
                modifier = modifier
                    .padding(end = 16.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    name: String,
    navigateBack: () -> Unit
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = name,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        windowInsets = WindowInsets(0.dp),
        modifier = modifier.padding(top = if (getPlatform().type == Type.NON_MOBILE) 24.dp else 0.dp)
    )
}

fun Modifier.parallaxLayoutModifier(scrollState: ScrollState, rate: Int) =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val height = if (rate > 0) scrollState.value / rate else scrollState.value
        layout(placeable.width, placeable.height) {
            placeable.place(0, height)
        }
    }