package planets.domain.service

import kotlinx.rpc.RemoteService
import kotlinx.rpc.annotations.Rpc
import planets.domain.domain.Planet

@Rpc
interface PlanetsService : RemoteService {
    suspend fun getPlanets(): List<Planet>?
}