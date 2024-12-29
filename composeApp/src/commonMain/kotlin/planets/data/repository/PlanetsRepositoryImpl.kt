package planets.data.repository

import planets.data.service.PlanetsService
import planets.domain.domain.Planet
import planets.domain.repository.PlanetsRepository

class PlanetsRepositoryImpl(
    private val service: PlanetsService,
) : PlanetsRepository {

    override fun getPlanets(): List<Planet> {
        return service.getPlanets()
    }

    override fun getPlanetById(planetId: Int): Planet? {
       return service.getPlanetById(planetId)
    }

}