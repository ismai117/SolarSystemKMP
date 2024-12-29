package planets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import getPlatform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import planets.domain.domain.Planet
import planets.domain.repository.PlanetsRepository

data class PlanetsState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val planet: Planet? = null,
    val message: String = ""
)

class PlanetsViewModel(
    private val planetsRepository: PlanetsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlanetsState())
    val state = _state.asStateFlow()

    init {
        getPlanets()
    }

    private fun getPlanets() {
        viewModelScope.launch(getPlatform().dispatcherIO) {
            _state.update { it.copy(isLoading = true) }
            val planets = planetsRepository.getPlanets()
            if (planets.isNotEmpty()){
                _state.update { it.copy(isLoading = false, planets = planets) }
            }
        }
    }

    fun getPlanetById(planetId: Int) {
        viewModelScope.launch(getPlatform().dispatcherIO) {
            _state.update { it.copy(planet = planetsRepository.getPlanetById(planetId)) }
        }
    }

}