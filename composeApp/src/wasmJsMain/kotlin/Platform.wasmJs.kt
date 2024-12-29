import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WasmPlatform: Platform {
    override val name: String = "web/wasm"
    override val type: Type = Type.NON_MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.Unconfined
    override fun openUrl(url: String?) {
        url?.let {  }
    }
}

actual fun getPlatform(): Platform = WasmPlatform()