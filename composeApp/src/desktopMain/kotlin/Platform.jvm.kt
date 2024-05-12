import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val type: Platforms = Platforms.DESKTOP
    override val isMobile: Boolean = false
    override val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
}

actual fun getPlatform(): Platform = JVMPlatform()