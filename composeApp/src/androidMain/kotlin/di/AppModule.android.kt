package di

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import main.planets.data.local.PlanetEntity
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import org.ncgroup.solarsystemkmp.SolarSystemApplication

actual fun dataModule(): Module  = module {
    val filesDir: String = SolarSystemApplication.INSTANCE.filesDir.path
    single<KStore<List<PlanetEntity>>> {
        storeOf(file = "$filesDir/planets.json".toPath(), default = emptyList())
    }
}