package di

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf
import main.planets.data.local.PlanetEntity
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun dataModule(): Module = module {
    single<KStore<List<PlanetEntity>>> {
        storeOf(key = "planets", default = emptyList())
    }
}