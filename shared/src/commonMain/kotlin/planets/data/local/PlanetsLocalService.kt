package planets.data.local

import io.github.xxfast.kstore.KStore
import planets.domain.domain.Planet

class PlanetsLocalService(
    private val kstore: KStore<List<Planet>>,
) {
    suspend fun getPlanets(): List<Planet>? {
        return kstore.get()
    }

    suspend fun setPlanets(planets: List<Planet>) {
        kstore.set(planets)
    }

    suspend fun getPlanetById(planetId: Int): Planet? {
        return kstore.get()?.find { it.planetId == planetId }
    }
}
