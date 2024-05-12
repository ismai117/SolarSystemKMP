package main.planets.presentation


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.graphics.Color
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

            LazyRow(
                modifier = modifier.fillMaxSize(),
                state = lazyListState,
                userScrollEnabled = false
            ) {

                itemsIndexed(
                    items = state.planets
                ) { index, planet ->

                    Column(
                        modifier = modifier
                            .fillParentMaxWidth()
                            .fillMaxHeight(),
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

                        Row(
                            modifier = modifier
                                .width(324.dp)
                        ) {

                            Box(
                                modifier = modifier
                                    .padding(start = 16.dp)
                                    .size(50.dp),
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
                                            modifier = modifier.fillMaxSize()
                                        )
                                    }
                                }

                            }

                            Column(
                                modifier = modifier
                                    .weight(1f),
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

                            Box(
                                modifier = modifier
                                    .padding(end = 16.dp)
                                    .size(50.dp),
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
                                            modifier = modifier.fillMaxSize()
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