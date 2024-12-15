import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.storage.storeOf
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import planets.domain.domain.Planet

class JsPlatform: Platform {
    override val name: String = "web/js"
    override val type: Type = Type.NON_MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.Unconfined
    override fun openUrl(url: String?) {
        url?.let { window.open(it) }
    }
}

actual fun getPlatform(): Platform = JsPlatform()

actual fun kstoreModule(): Module = module {
    single<KStore<List<Planet>>> {
        storeOf(key = "planets", default = emptyList())
    }
}

actual val DEV_SERVER_HOST: String = "127.0.0.1"