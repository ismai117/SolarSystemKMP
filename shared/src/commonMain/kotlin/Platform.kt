import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module

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

expect fun kstoreModule(): Module