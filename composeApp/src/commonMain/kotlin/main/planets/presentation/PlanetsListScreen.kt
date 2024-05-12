package main.planets.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowCircleDown
import androidx.compose.material.icons.outlined.ArrowCircleLeft
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material.icons.outlined.ArrowCircleUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import getPlatform
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@Composable
fun PlanetsListScreen(
    navigateToPlanetDetailScreen: (Int) -> Unit
) {

    PlanetsListScreenContent(
        navigateToPlanetDetailScreen = navigateToPlanetDetailScreen
    )

}

@Composable
fun PlanetsListScreenContent(
    modifier: Modifier = Modifier,
    navigateToPlanetDetailScreen: (Int) -> Unit
) {

    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    val planetsViewModel = koinInject<PlanetsViewModel>()
    val state = planetsViewModel.state

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->

        Box(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {

            if (getPlatform().isMobile) {

                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    state = lazyListState,
                    userScrollEnabled = false
                ) {

                    itemsIndexed(
                        items = state.planets
                    ) { index, planet ->

                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight()
//                            .border(width = 1.dp, color = Color.White)
                        ) {

                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(90.dp),
//                                .border(width = 1.dp, color = Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                if (index > 0) {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                lazyListState.animateScrollToItem(index - 1)
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.ArrowCircleUp,
                                            contentDescription = null,
                                            modifier = modifier
                                                .size(100.dp)
                                        )
                                    }
                                }

                            }

                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .weight(1f),
//                                .border(width = 1.dp, color = Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Box(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height(400.dp)
//                                        .border(width = 1.dp, color = Color.White),
                                    ) {
                                        AsyncImage(
                                            modifier = modifier.fillMaxSize(),
                                            model = planet.img,
                                            contentDescription = planet.imgDescription,
                                            contentScale = ContentScale.Fit
                                        )
                                    }

                                    Spacer(modifier = modifier.padding(16.dp))

                                    Column(
                                        modifier = modifier
                                            .fillMaxWidth(),
//                                        .border(width = 1.dp, color = Color.White),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = planet.name,
                                            fontSize = 32.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = modifier.padding(8.dp))

                                        OutlinedButton(
                                            onClick = {
                                                navigateToPlanetDetailScreen(planet.planetId)
                                            }
                                        ){
                                            Text(
                                                text = "View",
                                                fontWeight = FontWeight.Bold
                                            )
                                        }

                                    }

                                }

                            }

                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(90.dp),
//                                .border(width = 1.dp, color = Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                if (planet != state.planets.last()) {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                val currentIndex = state.planets.indexOf(planet)
                                                val nextIndex = if (currentIndex < state.planets.size - 1) currentIndex + 1 else 0
                                                lazyListState.animateScrollToItem(nextIndex)
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.ArrowCircleDown,
                                            contentDescription = null,
                                            modifier = modifier
                                                .size(100.dp)
                                        )
                                    }
                                }

                            }

                        }


                    }
                }

            } else {

                LazyRow(
                    modifier = modifier.fillMaxSize(),
                    state = lazyListState,
                    userScrollEnabled = false
                ) {

                    itemsIndexed(
                        items = state.planets
                    ) { index, planet ->

                        Row(
                            modifier = modifier
                                .fillParentMaxWidth()
                                .fillMaxHeight()
//                            .border(width = 1.dp, color = Color.White)
                        ) {

                            Box(
                                modifier = modifier
                                    .width(100.dp)
                                    .fillMaxHeight(),
//                                .border(width = 1.dp, color = Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                if (index > 0) {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                lazyListState.animateScrollToItem(index - 1)
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.ArrowCircleLeft,
                                            contentDescription = null,
                                            modifier = modifier
                                                .size(100.dp)
                                        )
                                    }
                                }

                            }

                            Box(
                                modifier = modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
//                                .border(width = 1.dp, color = Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Box(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .height(400.dp)
//                                        .border(width = 1.dp, color = Color.White),
                                    ) {
                                        AsyncImage(
                                            modifier = modifier.fillMaxSize(),
                                            model = planet.img,
                                            contentDescription = planet.imgDescription,
                                            contentScale = ContentScale.Fit
                                        )
                                    }

                                    Spacer(modifier = modifier.padding(24.dp))

                                    Column(
                                        modifier = modifier
                                            .fillMaxWidth(),
//                                        .border(width = 1.dp, color = Color.White),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = planet.name,
                                            fontSize = 32.sp,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = modifier.padding(12.dp))

                                        OutlinedButton(
                                            onClick = {
                                                navigateToPlanetDetailScreen(planet.planetId)
                                            }
                                        ){
                                            Text(
                                                text = "View",
                                                fontWeight = FontWeight.Bold
                                            )
                                        }

                                    }

                                }

                            }

                            Box(
                                modifier = modifier
                                    .width(100.dp)
                                    .fillMaxHeight(),
//                                .border(width = 1.dp, color = Color.White),
                                contentAlignment = Alignment.Center
                            ) {

                                if (planet != state.planets.last()) {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                val currentIndex = state.planets.indexOf(planet)
                                                val nextIndex = if (currentIndex < state.planets.size - 1) currentIndex + 1 else 0
                                                lazyListState.animateScrollToItem(nextIndex)
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.ArrowCircleRight,
                                            contentDescription = null,
                                            modifier = modifier
                                                .size(100.dp)
                                        )
                                    }
                                }

                            }

                        }


                    }
                }

            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = modifier.align(Alignment.Center)
                )
            }

        }

    }

}