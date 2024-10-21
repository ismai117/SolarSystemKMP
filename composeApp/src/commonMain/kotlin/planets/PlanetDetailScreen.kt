package planets

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.sizeIn
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
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberImagePainter
import getPlatform
import org.koin.compose.koinInject
import planets.domain.domain.Planet
import planets.viewmodel.PlanetsViewModel


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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PlanetDetailScreenContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    navigateBack: () -> Unit
) {

    val windowSizeClass = calculateWindowSizeClass()
    val isCompacted = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    Scaffold { paddingValues ->

        Box(
            modifier = modifier.padding(paddingValues)
        ) {

            if (getPlatform().type == Type.MOBILE) {

                Mobile(
                    animatedVisibilityScope = animatedVisibilityScope,
                    planet = planet,
                    navigateBack = navigateBack
                )

            } else {

                if(isCompacted){

                    Mobile(
                        animatedVisibilityScope = animatedVisibilityScope,
                        planet = planet,
                        navigateBack = navigateBack
                    )

                } else {

                    NonMobile(
                        animatedVisibilityScope = animatedVisibilityScope,
                        planet = planet,
                        navigateBack = navigateBack
                    )

                }


            }

        }

    }

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.Mobile(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    navigateBack: () -> Unit
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
//                .border(width = 1.dp, color = Color.White)
    ) {

        TopBar(
            animatedVisibilityScope = animatedVisibilityScope,
            name = planet.name,
            planetId = planet.planetId,
            navigateBack = navigateBack
        )

        Box(
            modifier = modifier
                .padding(top = 40.dp)
                .size(300.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
                .parallaxLayoutModifier(scrollState, 2)
//                                        .border(width = 1.dp, color = Color.White),
        ) {
            val painter = rememberImagePainter(url = planet.img)
            Image(
                painter = painter,
                contentDescription =  planet.imgDescription,
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

        Column(
            modifier = modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        ) {

            PlanetDetailItems(
                planet = planet
            )

        }


    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.NonMobile(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    planet: Planet,
    navigateBack: () -> Unit
){

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
    ){

        TopBar(
            animatedVisibilityScope = animatedVisibilityScope,
            name = planet.name,
            planetId = planet.planetId,
            navigateBack = navigateBack
        )

        Row(
            modifier = modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = modifier
                        .sizeIn(maxWidth = 500.dp, maxHeight = 500.dp)
                        .clip(CircleShape)
                ) {
                    val painter = rememberImagePainter(url = planet.img)
                    Image(
                        painter = painter,
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

                    PlanetDetailItems(
                        planet = planet
                    )

                }

            }

        }

    }
}

@Composable
private fun PlanetDetailItems(
    modifier: Modifier = Modifier,
    planet: Planet
){

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
                    text = "Order",
                    fontSize = 16.sp,
                    modifier = modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = planet.planetOrder.toString(),
                    fontSize = 16.sp,
                    modifier = modifier
                        .padding(end = 16.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }

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
                    text = "Mass",
                    fontSize = 16.sp,
                    modifier = modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = planet.mass,
                    fontSize = 16.sp,
                    modifier = modifier
                        .padding(end = 16.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }

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
                    text = "Volume",
                    fontSize = 16.sp,
                    modifier = modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = planet.volume,
                    fontSize = 16.sp,
                    modifier = modifier
                        .padding(end = 16.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }

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
                    text = planet.source,
                    fontSize = 16.sp,
                    modifier = modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart)
                )
                IconButton(
                    onClick = {
                        getPlatform().openUrl(planet.wikiLink)
                    },
                    modifier = modifier
                        .padding(end = 16.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = null
                    )
                }
            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.TopBar(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    name: String,
    planetId: Int,
    navigateBack: () -> Unit
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = name,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
//                modifier = modifier
//                    .sharedElement(
//                        state = rememberSharedContentState(key = "name/${planetId}"),
//                        animatedVisibilityScope = animatedVisibilityScope,
//                        boundsTransform = { _, _ ->
//                            tween(durationMillis = 1000)
//                        }
//                    ),
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