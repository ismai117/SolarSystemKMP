package planets.domain.repository

import planets.domain.domain.Planet

interface PlanetsRepository {
    fun getPlanets(): List<Planet>
    fun getPlanetById(planetId: Int): Planet?
}