package routes

import data.planets.repository.PlanetsRepository
import data.planets.repository.PlanetsRepositoryImpl
import data.planets.service.PlanetsService
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

fun Routing.planets(){
    val planetsRepository: PlanetsRepository by lazy { PlanetsRepositoryImpl() }
    val planetsService: PlanetsService by lazy { PlanetsService(planetsRepository) }
    get("/planets"){
        call.respond(
            HttpStatusCode.OK,
            planetsService.getPlanets()
        )
    }
}