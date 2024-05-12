import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

enum class Platforms {
    ANDROID,
    IOS,
    DESKTOP,
    WEB_JS,
    WEB_WASM
}

interface Platform {
    val name: String
    val type: Platforms
    val isMobile: Boolean
    val coroutineDispatcher: CoroutineDispatcher
}

expect fun getPlatform(): Platform
