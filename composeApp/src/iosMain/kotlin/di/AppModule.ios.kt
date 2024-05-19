package di

import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import io.github.xxfast.kstore.file.utils.DocumentDirectory
import io.github.xxfast.kstore.utils.ExperimentalKStoreApi
import main.planets.data.local.PlanetEntity
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSFileManager

@OptIn(ExperimentalKStoreApi::class)
actual fun dataModule(): Module = module {
    single<KStore<List<PlanetEntity>>> {
        val filesDir: String? = NSFileManager.defaultManager.DocumentDirectory?.relativePath
        requireNotNull(filesDir) { "Document directory not found" }
        storeOf(file = "$filesDir/planets.json".toPath(), default = emptyList())
    }
}