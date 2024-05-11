package main.planets.presentation

import main.planets.domain.domain.Planet

data class PlanetsState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val planet: Planet? = null,
    val error: String = ""
)
