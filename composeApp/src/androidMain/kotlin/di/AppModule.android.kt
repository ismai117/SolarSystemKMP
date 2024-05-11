package di

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import main.planets.data.local.model.PlanetEntity
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import org.ncgroup.solarsystemkmp.SolarSystemApplication

actual fun dataModule(): Module  = module {
    single<KStore<List<PlanetEntity>>> {
        val filesDir: String = SolarSystemApplication.INSTANCE.filesDir.path
        storeOf(file = "$filesDir/planets.json".toPath(), default = emptyList())
    }
}