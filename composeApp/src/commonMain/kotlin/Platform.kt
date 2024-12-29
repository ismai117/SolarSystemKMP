import kotlinx.coroutines.CoroutineDispatcher

enum class Type {
    MOBILE,
    NON_MOBILE
}

interface Platform {
    val name: String
    val type: Type
    val dispatcherIO: CoroutineDispatcher
    fun openUrl(url: String?)
}

expect fun getPlatform(): Platform