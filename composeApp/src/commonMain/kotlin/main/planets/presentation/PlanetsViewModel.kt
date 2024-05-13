package main.planets.presentation

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import getPlatform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import main.planets.domain.repository.PlanetsRepository
import utils.Resource

class PlanetsViewModel(
    private val planetsRepository: PlanetsRepository
) : ViewModel() {

    var state by mutableStateOf(PlanetsState())

    init {
        getPlanets()
    }

    private fun getPlanets() {
        viewModelScope.launch(getPlatform().coroutineDispatcher) {
            planetsRepository.getPlanets().collect { result ->
                viewModelScope.launch(Dispatchers.Main){
                    state = when (result) {
                        is Resource.Loading -> {
                            state.copy(
                                loading = true
                            )
                        }

                        is Resource.Success -> {
                            state.copy(
                                loading = false,
                                planets = result.data.orEmpty()
                            )
                        }

                        is Resource.Error -> {
                            state.copy(
                                loading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }

    fun getPlanetById(planetId: Int) {
        viewModelScope.launch(getPlatform().coroutineDispatcher) {
            planetsRepository.getPlanetById(planetId).collect {
                state = state.copy(planet = it)
            }
        }
    }

}