import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import io.github.xxfast.kstore.file.utils.DocumentDirectory
import io.github.xxfast.kstore.utils.ExperimentalKStoreApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import planets.data.local.PlanetEntity
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class IOSPlatform: Platform {
    override val name: String = "ios"
    override val type: Type = Type.MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    override fun openUrl(url: String?) {
        val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
        UIApplication.sharedApplication.openURL(nsUrl)
    }
}

actual fun getPlatform(): Platform = IOSPlatform()

@OptIn(ExperimentalKStoreApi::class)
actual fun kstoreModule(): Module = module {
    single<KStore<List<PlanetEntity>>> {
        val filesDir: String? = NSFileManager.defaultManager.DocumentDirectory?.relativePath
        requireNotNull(filesDir) { "Document directory not found" }
        storeOf(file = "$filesDir/planets.json".toPath(), default = emptyList())
    }
}