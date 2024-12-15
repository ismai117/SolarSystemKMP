import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import io.github.xxfast.kstore.file.utils.FILE_SYSTEM
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.harawata.appdirs.AppDirsFactory
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import planets.domain.domain.Planet
import java.awt.Desktop
import java.net.URI

class JVMPlatform: Platform {
    override val name: String = "desktop"
    override val type: Type = Type.NON_MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    override fun openUrl(url: String?) {
        val uri = url?.let { URI.create(it) } ?: return
        Desktop.getDesktop().browse(uri)
    }
}

actual fun getPlatform(): Platform = JVMPlatform()

private const val PACKAGE_NAME = "org.ncgroup.solarsystemkmp"
private const val VERSION = "1.0.0"
private const val AUTHOR = "ismailmohamed"

actual fun kstoreModule(): Module = module {
    single<KStore<List<Planet>>> {
        val filesDir: String = AppDirsFactory.getInstance()
            .getUserDataDir(PACKAGE_NAME, VERSION, AUTHOR)

        FILE_SYSTEM.createDirectories(filesDir.toPath())

        storeOf(file = "$filesDir/planets.json".toPath(), default = emptyList())
    }
}

actual val DEV_SERVER_HOST: String = "127.0.0.1"