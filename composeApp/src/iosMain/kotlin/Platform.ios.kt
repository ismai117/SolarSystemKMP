import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val type: Platforms  = Platforms.MOBILE
    override val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
}

actual fun getPlatform(): Platform = IOSPlatform()