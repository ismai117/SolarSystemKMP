package data.planets.repository

import planets.domain.domain.Planet

interface PlanetsRepository {
    fun getPlanets(): List<Planet>
}