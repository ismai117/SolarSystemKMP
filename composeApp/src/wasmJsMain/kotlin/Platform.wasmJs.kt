import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val type: Platforms = Platforms.NON_MOBILE
    override val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
}

actual fun getPlatform(): Platform = WasmPlatform()