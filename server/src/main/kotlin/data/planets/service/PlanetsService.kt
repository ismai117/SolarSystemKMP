package data.planets.service

import data.planets.repository.PlanetsRepository
import planets.domain.domain.Planet

class PlanetsService (
    private val planetsRepository: PlanetsRepository
){
    fun getPlanets(): List<Planet> = planetsRepository.getPlanets()
}
