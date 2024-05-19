package main.planets.data.local


import io.github.xxfast.kstore.KStore
import main.planets.domain.domain.Planet


class PlanetsLocalService(
    private val kstore: KStore<List<PlanetEntity>>,
) {
    suspend fun getPlanets(): List<Planet>? {
        return kstore.get()?.mapToDomainModelList()
    }

    suspend fun setPlanets(planets: List<Planet>){
        kstore.set(planets.mapFromDomainModelList())
    }

    suspend fun getPlanetById(planetId: Int): Planet? {
        return kstore.get()?.find { it.planetId == planetId }?.mapToDomainModel()
    }

}
