package main.planets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import getPlatform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import main.planets.domain.domain.Planet
import main.planets.domain.repository.PlanetsRepository

class PlanetsViewModel(
    private val planetsRepository: PlanetsRepository
) : ViewModel() {

    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets =  _planets.asStateFlow()

    private val _planet = MutableStateFlow<Planet?>(null)
    val planet = _planet.asStateFlow()

    private val _isPlanetsLoading = MutableStateFlow(false)
    val isPlanetsLoading = _isPlanetsLoading.asStateFlow()

    init {
        getPlanets()
    }

    private fun getPlanets() {
        viewModelScope.launch(getPlatform().coroutineDispatcher) {
            _isPlanetsLoading.value = true
            if (planetsRepository.getPlanets().isNotEmpty()){
                _isPlanetsLoading.value = false
                _planets.value = planetsRepository.getPlanets()
            }
        }
    }

    fun getPlanetById(planetId: Int) {
        viewModelScope.launch(getPlatform().coroutineDispatcher) {
            _planet.value = planetsRepository.getPlanetById(planetId)
        }
    }

}