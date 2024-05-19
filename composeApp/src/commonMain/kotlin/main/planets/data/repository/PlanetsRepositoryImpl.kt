package main.planets.data.repository

import main.planets.data.local.PlanetsLocalService
import main.planets.data.remote.PlanetsRemoteService
import main.planets.domain.domain.Planet
import main.planets.domain.repository.PlanetsRepository


class PlanetsRepositoryImpl(
    private val remote: PlanetsRemoteService,
    private val local: PlanetsLocalService
) : PlanetsRepository {

    override suspend fun getPlanets(): List<Planet> {
        val planets = local.getPlanets()
        if (planets.isNullOrEmpty()) return remote.getPlanets().also { local.setPlanets(it) }
        return planets
    }

    override suspend fun getPlanetById(planetId: Int): Planet? {
       return local.getPlanetById(planetId)
    }

}