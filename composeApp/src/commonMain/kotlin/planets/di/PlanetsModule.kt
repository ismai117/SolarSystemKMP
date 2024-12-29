package planets.di

import org.koin.dsl.module
import planets.data.service.PlanetsService
import planets.data.repository.PlanetsRepositoryImpl
import planets.domain.repository.PlanetsRepository
import planets.presentation.PlanetsViewModel

val planetsModule = module {
    single { PlanetsService() }
    single<PlanetsRepository> {  PlanetsRepositoryImpl(get()) }
    factory { PlanetsViewModel(get()) }
}