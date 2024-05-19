package main.planets.domain.repository



import main.planets.domain.domain.Planet

interface PlanetsRepository {
    suspend fun getPlanets(): List<Planet>
    suspend fun getPlanetById(planetId: Int): Planet?

}