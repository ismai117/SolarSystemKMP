import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class IOSPlatform: Platform {
    override val name: String = "ios"
    override val type: Type = Type.MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    override fun openUrl(url: String?) {
        val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
        UIApplication.sharedApplication.openURL(nsUrl)
    }
}

actual fun getPlatform(): Platform = IOSPlatform()