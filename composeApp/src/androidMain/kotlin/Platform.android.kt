import android.content.Intent
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.ncgroup.solarsystemkmp.SolarSystemKMPApp

class AndroidPlatform : Platform {
    override val name: String = "android"
    override val type: Type = Type.MOBILE
    override val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
    override fun openUrl(url: String?) {
        val uri = url?.let { Uri.parse(it) } ?: return
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = uri
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        SolarSystemKMPApp.INSTANCE.startActivity(intent)
    }
}

actual fun getPlatform(): Platform = AndroidPlatform()