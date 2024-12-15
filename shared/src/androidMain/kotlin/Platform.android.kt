import android.content.Intent
import android.net.Uri
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.dsl.module
import planets.domain.domain.Planet

class AndroidPlatform : Platform {
    override val name: String = "android"
    override val type: Type = Type.MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    override fun openUrl(url: String?) {
        val uri = url?.let { Uri.parse(it) } ?: return
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = uri
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        applicationContext.startActivity(intent)
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun kstoreModule(): Module = module {
    single<KStore<List<Planet>>> {
        val filesDir: String = applicationContext.filesDir.path
        storeOf(file = "$filesDir/planets.json".toPath(), default = emptyList())
    }
}

actual val DEV_SERVER_HOST: String = "10.0.2.2"