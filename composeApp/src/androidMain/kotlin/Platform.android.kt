import android.os.Build
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: Platforms  = Platforms.MOBILE
    override val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
}

actual fun getPlatform(): Platform = AndroidPlatform()