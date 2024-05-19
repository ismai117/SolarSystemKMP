package main.planets.di

import main.planets.data.local.PlanetsLocalService
import main.planets.data.remote.PlanetsRemoteService
import main.planets.data.repository.PlanetsRepositoryImpl
import main.planets.domain.repository.PlanetsRepository
import main.planets.presentation.PlanetsViewModel
import org.koin.dsl.module

val planetsModule = module {
    single { PlanetsRemoteService() }
    single { PlanetsLocalService(get())}
    single<PlanetsRepository> {  PlanetsRepositoryImpl(get(), get()) }
    factory { PlanetsViewModel(get()) }
}