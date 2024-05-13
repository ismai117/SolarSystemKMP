package main.planets.data.repository

import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import main.planets.data.local.mapper.mapFromDomainModelList
import main.planets.data.local.mapper.mapToDomainModel
import main.planets.data.local.mapper.mapToDomainModelList
import main.planets.data.local.service.PlanetsLocalService
import main.planets.data.remote.mapper.mapToDomainModelList
import main.planets.data.remote.model.PlanetDto
import main.planets.data.remote.service.PlanetsRemoteService
import main.planets.domain.domain.Planet
import main.planets.domain.repository.PlanetsRepository
import utils.Resource
import utils.networkBoundResource


class PlanetsRepositoryImpl(
    private val planetsRemoteService: PlanetsRemoteService,
    private val planetsLocalService: PlanetsLocalService
) : PlanetsRepository {

    override suspend fun getPlanets(): Flow<Resource<List<Planet>?>> {
        return networkBoundResource(
            query = {
                flowOf(planetsLocalService.selectAllPlanets()?.mapToDomainModelList())
            },
            fetch = {
                val request = planetsRemoteService.getPlanets()
                val response = request.body<List<PlanetDto>>()
                response.mapToDomainModelList()
            },
            saveFetchResult = {
                planetsLocalService.insertPlanets(it.mapFromDomainModelList())
            },
            shouldFetch = {
                it.isNullOrEmpty()
            }
        )
    }

    override suspend fun getPlanetById(planetId: Int): Flow<Planet?> {
        return flowOf(planetsLocalService.selectPlanetById(planetId)?.mapToDomainModel())
    }

}