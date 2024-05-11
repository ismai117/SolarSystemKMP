import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

enum class Platforms {
    MOBILE,
    NON_MOBILE
}

interface Platform {
    val name: String
    val type: Platforms
    val coroutineDispatcher: CoroutineDispatcher
}

expect fun getPlatform(): Platform
