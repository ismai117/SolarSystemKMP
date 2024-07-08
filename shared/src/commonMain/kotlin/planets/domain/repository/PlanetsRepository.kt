package planets.domain.repository

import planets.domain.domain.Planet

interface PlanetsRepository {
    suspend fun getPlanets(): List<Planet>
    suspend fun getPlanetById(planetId: Int): Planet?
}