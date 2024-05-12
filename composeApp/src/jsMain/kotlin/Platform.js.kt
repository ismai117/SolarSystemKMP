import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class JsPlatform: Platform {
    override val name: String = "Web with Kotlin/Js"
    override val type: Platforms  = Platforms.WEB_JS
    override val isMobile: Boolean = false
    override val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default
}

actual fun getPlatform(): Platform = JsPlatform()