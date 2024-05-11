package main.planets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewModelScope
import getPlatform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
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
            planetsRepository.getPlanets()
                .catch {
                    state = state.copy(isLoading = false, error = it.message.toString())
                }
                .collect { result ->
                    viewModelScope.launch(Dispatchers.Main) {
                        state = when (result) {
                            is Resource.Loading -> {
                                state.copy(isLoading = true)
                            }

                            is Resource.Success -> {
                                state.copy(isLoading = false, planets = result.data.orEmpty())
                            }

                            is Resource.Error -> {
                                state.copy(isLoading = false, error = result.message)
                            }
                        }
                    }
                }
        }
    }

    fun getPlanetById(planetId: Int) {
        viewModelScope.launch(getPlatform().coroutineDispatcher) {
            planetsRepository.getPlanetById(planetId)
                .catch {
                    it.printStackTrace()
                }
                .collect { result ->
                    state = state.copy(planet = result)
                }
        }
    }

}