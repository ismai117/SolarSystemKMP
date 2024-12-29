import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.awt.Desktop
import java.net.URI

class JVMPlatform: Platform {
    override val name: String = "desktop"
    override val type: Type = Type.NON_MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    override fun openUrl(url: String?) {
        val uri = url?.let { URI.create(it) } ?: return
        Desktop.getDesktop().browse(uri)
    }
}

actual fun getPlatform(): Platform = JVMPlatform()