package di

import org.koin.core.module.Module
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import io.github.xxfast.kstore.file.utils.FILE_SYSTEM
import main.planets.data.local.PlanetEntity
import net.harawata.appdirs.AppDirsFactory
import okio.Path.Companion.toPath
import org.koin.dsl.module


private const val PACKAGE_NAME = "org.ncgroup.solarsystemkmp"
private const val VERSION = "1.0.0"
private const val AUTHOR = "ismailmohamed"

actual fun dataModule(): Module = module {
    single<KStore<List<PlanetEntity>>> {
        val filesDir: String = AppDirsFactory.getInstance()
            .getUserDataDir(PACKAGE_NAME, VERSION, AUTHOR)

        FILE_SYSTEM.createDirectories(filesDir.toPath())

        storeOf(file = "$filesDir/planets.json".toPath(), default = emptyList())
    }
}
