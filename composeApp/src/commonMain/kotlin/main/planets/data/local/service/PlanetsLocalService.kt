package main.planets.data.local.service

import io.github.xxfast.kstore.KStore
import main.planets.data.local.model.PlanetEntity


class PlanetsLocalService(
    private val store: KStore<List<PlanetEntity>>
) {

    suspend fun selectAllPlanets(): List<PlanetEntity>? {
        return store.get()
    }

    suspend fun selectPlanetById(planetId: Int): PlanetEntity? {
        return store.get()?.find { it.planetId == planetId }
    }

    suspend fun insert(planetEntity: List<PlanetEntity>) {
        store.set(planetEntity)
    }

}