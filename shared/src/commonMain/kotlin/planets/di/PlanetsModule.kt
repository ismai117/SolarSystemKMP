package planets.di

import org.koin.dsl.module
import planets.data.local.PlanetsLocalService
import planets.data.remote.PlanetsRemoteService
import planets.data.repository.PlanetsRepositoryImpl
import planets.domain.repository.PlanetsRepository
import planets.viewmodel.PlanetsViewModel

val planetsModule = module {
    single { PlanetsRemoteService() }
    single { PlanetsLocalService(get()) }
    single<PlanetsRepository> { PlanetsRepositoryImpl(get(), get()) }
    factory { PlanetsViewModel(get()) }
}