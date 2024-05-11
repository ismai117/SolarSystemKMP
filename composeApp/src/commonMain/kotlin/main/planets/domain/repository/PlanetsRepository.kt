package main.planets.domain.repository

import kotlinx.coroutines.flow.Flow
import main.planets.domain.domain.Planet
import utils.Resource

interface PlanetsRepository {
    suspend fun getPlanets(): Flow<Resource<List<Planet>?>>
    suspend fun getPlanetById(planetId: Int): Flow<Planet?>
}