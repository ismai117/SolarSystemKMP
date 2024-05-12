import androidx.compose.ui.text.toLowerCase
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val type: Platforms  = Platforms.WEB_WASM
    override val isMobile: Boolean = false
    override val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
}

actual fun getPlatform(): Platform = WasmPlatform()