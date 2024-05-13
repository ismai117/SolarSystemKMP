package main.planets.data.local.service

import io.github.xxfast.kstore.KStore
import main.planets.data.local.model.PlanetEntity


class PlanetsLocalService(
    private val planets: KStore<List<PlanetEntity>>,
) {
    // planets

    suspend fun selectAllPlanets(): List<PlanetEntity>? {
        return planets.get()
    }

    suspend fun selectPlanetById(planetId: Int): PlanetEntity? {
        return planets.get()?.find { it.planetId == planetId }
    }

    suspend fun insertPlanets(planetEntity: List<PlanetEntity>) {
        planets.set(planetEntity)
    }

}